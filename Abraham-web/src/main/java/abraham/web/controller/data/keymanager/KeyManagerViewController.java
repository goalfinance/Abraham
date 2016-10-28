package abraham.web.controller.data.keymanager;

import abraham.core.ca.domain.RSAKeyInfo;
import abraham.core.ca.service.KeyPairService;
import abraham.core.ca.service.VOKeyPair;
import com.sun.xml.internal.bind.v2.runtime.output.Encoded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pan.utils.AppBizException;
import pan.utils.Encodes;

/**
 * Created by panqingrong on 29/09/2016.
 */
@Controller
@RequestMapping("/data/keymanager")
public class KeyManagerViewController {
    @Autowired
    private KeyPairService keyPairService;

    @RequestMapping("show_main_view")
    String showMainView(Model model){
        return "data/keymanager/MainView";
    }

    @RequestMapping("show_keypair_info_modal")
    String showKeyPairRSAInfo(@RequestParam(name="sId", required = true)String sId, Model model) throws AppBizException {
        RSAKeyInfo rsaKeyInfo = keyPairService.findKeyPairRSAInfoBySid(Long.valueOf(sId));
        rsaKeyInfo.setPublicKeyExponent(Encodes.hexString2Display(rsaKeyInfo.getPublicKeyExponent()));
        rsaKeyInfo.setPrivateKeyExponent(Encodes.hexString2Display(rsaKeyInfo.getPrivateKeyExponent()));
        rsaKeyInfo.setPrivateKeyModulus(Encodes.hexString2Display(rsaKeyInfo.getPrivateKeyModulus()));
        rsaKeyInfo.setPublicKeyModulus(Encodes.hexString2Display(rsaKeyInfo.getPublicKeyModulus()));
        model.addAttribute("rsaKeyInfo", rsaKeyInfo);
        return "data/keymanager/ShowKeyPairRSAInfo";
    }

    @RequestMapping("show_keypair_delete_confirm_info_modal")
    String showKeyPairDeleteConfirmInfo(@RequestParam(name="sId", required=true)String sId, Model model){
        RSAKeyInfo rsaKeyInfo = keyPairService.findKeyPairRSAInfoBySid(Long.valueOf(sId));
        model.addAttribute("sId", sId);
        model.addAttribute("keypairName", rsaKeyInfo.getName());
        return "data/keymanager/ShowKeyPairDeleteConfirmInfo";
    }
}
