package abraham.web.controller.data.keymanager;

import abraham.core.ca.domain.DSAKeyExtInfo;
import abraham.core.ca.domain.KeyPairInfo;
import abraham.core.ca.domain.RSAKeyExtInfo;
import abraham.core.isaac.bean.FileInfo;
import abraham.web.restcontroller.keymanager.beans.KeyExportReqBean;
import abraham.web.service.keymanager.KeyManagerService;
import abraham.web.service.keymanager.models.ExportKeyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import pan.utils.AppBizException;
import pan.utils.Encodes;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by panqingrong on 29/09/2016.
 */
@Controller
@RequestMapping("/data/keymanager")
public class KeyManagerViewController {
    @Autowired
    private KeyManagerService keyManagerService;

    @Autowired
    private RestTemplate restTemplateIsaac;

    @RequestMapping("show_main_view")
    String showMainView(Model model) {
        return "data/keymanager/MainView";
    }

    @RequestMapping("show_add_keypair_form_modal")
    String showAddKeyPairForm(Model model) throws AppBizException{
        return "data/keymanager/AddKeyPairForm";
    }

    @RequestMapping("show_keypair_info_modal")
    String showKeyPairRSAInfo(@RequestParam(name = "sId", required = true) String sId,
                              @RequestParam(name = "keyType", required = true) String keyType,
                              Model model) throws AppBizException {
        if (keyType.equalsIgnoreCase("RSA")){
            RSAKeyExtInfo rsaKeyInfo = (RSAKeyExtInfo) keyManagerService.findRSAKeyExtInfoBySid(Long.valueOf(sId));
            rsaKeyInfo.setPublicKeyExponent(Encodes.hexString2Display(rsaKeyInfo.getPublicKeyExponent()));
            rsaKeyInfo.setPrivateKeyExponent(Encodes.hexString2Display(rsaKeyInfo.getPrivateKeyExponent()));
            rsaKeyInfo.setPrivateKeyModulus(Encodes.hexString2Display(rsaKeyInfo.getPrivateKeyModulus()));
            rsaKeyInfo.setPublicKeyModulus(Encodes.hexString2Display(rsaKeyInfo.getPublicKeyModulus()));
            model.addAttribute("rsaKeyInfo", rsaKeyInfo);
            return "data/keymanager/ShowKeyPairRSAInfo";
        }else if (keyType.equalsIgnoreCase("DSA")){
            DSAKeyExtInfo dsaKeyInfo = (DSAKeyExtInfo) keyManagerService.findDSAKeyExtInfoBySid(Long.valueOf(sId));
            dsaKeyInfo.setG("Available");
            dsaKeyInfo.setX("Available");
            dsaKeyInfo.setP("Available");
            dsaKeyInfo.setY(Encodes.hexString2Display(dsaKeyInfo.getY()));
            dsaKeyInfo.setQ(dsaKeyInfo.getQ());
            model.addAttribute("dsaKeyInfo", dsaKeyInfo);
            return "data/keymanager/ShowKeyPairDSAInfo";
        }


        return "data/keymanager/ShowKeyPairRSAInfo";
    }

    @RequestMapping("show_keypair_delete_confirm_info_modal")
    String showKeyPairDeleteConfirmInfo(@RequestParam(name = "sId", required = true) String sId,
                                        @RequestParam(name = "keyType", required = true) String keyType,
                                        Model model) throws AppBizException{
        KeyPairInfo KeyPairInfo = keyManagerService.findKeyPairInfoBySid(Long.valueOf(sId));
        model.addAttribute("sId", sId);
        model.addAttribute("keypairName", KeyPairInfo.getName());
        return "data/keymanager/ShowKeyPairDeleteConfirmInfo";
    }

    @RequestMapping("show_export_key_info_modal")
    String showKeyExportInfo(@RequestParam(name = "sId", required = true) Long sId, Model model) throws AppBizException {
        KeyPairInfo keyInfo = keyManagerService.findKeyPairInfoBySid(sId);
        model.addAttribute("sId", sId);
        model.addAttribute("keypairName", keyInfo.getName());
        model.addAttribute("keypairFileName", keyInfo.getName());
        return "data/keymanager/ShowExportKeyInfo";
    }

    //Use Servlet 3 async to reduce thread occupied-time.
    //It is not a rest API, because AJAX cannot do file-downloading well.
    @RequestMapping(value = "export_key/{sId}", method = RequestMethod.POST)
    public StreamingResponseBody exportKey(@PathVariable("sId") Long sId, KeyExportReqBean keyExportReq, HttpServletResponse response) throws AppBizException {
        ExportKeyRequest exportKeyRequest = new ExportKeyRequest();
        exportKeyRequest.setKeySid(sId);
        exportKeyRequest.setKeyFileName(keyExportReq.getKeypairName());
        exportKeyRequest.setKeyExportFormat(keyExportReq.getKeypairExportFormat());

        //TODO To support more file format, not only .pem
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + exportKeyRequest.getKeyFileName() + ".pem");
        KeyPairInfo keyPairInfo = keyManagerService.findKeyPairInfoBySid(sId);
        String exportedFileId = keyPairInfo.getExported();

        return (OutputStream outputStream)->{
            if (exportedFileId != null && !exportedFileId.equals("")){
                FileInfo file = restTemplateIsaac.getForObject("http://isaac/filemanager/file/" + exportedFileId, FileInfo.class);
                if (file != null){
                    outputStream.write(file.getContent());
                    outputStream.flush();
                }else{
                    //todo: If the file does not exist in FileManager, we need some codes to handle this.
                }
            }else{
                ByteArrayOutputStream byteArrayOutputStream = null;
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    keyManagerService.exportKey(exportKeyRequest, byteArrayOutputStream);
                    FileInfo file = new FileInfo();

                    file.setContent(byteArrayOutputStream.toByteArray());
                    file = restTemplateIsaac.postForObject("http://isaac/filemanager/file", file, FileInfo.class);

                    keyPairInfo.setExported(file.getId());
                    keyManagerService.updateKeyPairInfo(keyPairInfo);

                    outputStream.write(byteArrayOutputStream.toByteArray());
                    outputStream.flush();

                } catch (Throwable e) {

                    throw new IOException(e.getMessage(),e);
                }finally {
                    if (byteArrayOutputStream != null){
                        byteArrayOutputStream.close();
                    }
                }

            }


        };
    }

}
