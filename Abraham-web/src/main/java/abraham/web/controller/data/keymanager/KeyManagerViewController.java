package abraham.web.controller.data.keymanager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by panqingrong on 29/09/2016.
 */
@Controller
@RequestMapping("/data/keymanager")
public class KeyManagerViewController {
    @RequestMapping("show_main_view")
    String showMainView(Model model){
        return "data/keymanager/MainView";
    }
}
