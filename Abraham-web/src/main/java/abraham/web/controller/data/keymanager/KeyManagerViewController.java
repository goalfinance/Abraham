package abraham.web.controller.data.keymanager;

import abraham.core.ca.domain.DSAKeyExtInfo;
import abraham.core.ca.domain.KeyPairInfo;
import abraham.core.ca.domain.RSAKeyExtInfo;
import abraham.core.ca.service.KeyService;
import abraham.web.service.keymanager.KeyManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pan.utils.AppBizException;
import pan.utils.Encodes;

import java.util.Map;

/**
 * Created by panqingrong on 29/09/2016.
 */
@Controller
@RequestMapping("/data/keymanager")
public class KeyManagerViewController {
    @Autowired
    private KeyManagerService keyManagerService;

    @RequestMapping("show_main_view")
    String showMainView(Model model) {
        return "data/keymanager/MainView";
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
    String showKeyExportInfo(@RequestParam(name = "sId", required = true) Long sId, Model model) {
//        RSAKeyExtInfo rsaKeyInfo = keyPairService.findKeyPairRSAInfoBySid(sId);
//        model.addAttribute("sId", sId);
//        model.addAttribute("keypairName", rsaKeyInfo.getName());
//        model.addAttribute("keypairFileName", rsaKeyInfo.getName());
        return "data/keymanager/ShowExportKeyInfo";
    }
}
