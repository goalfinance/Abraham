package abraham.web.controller.data.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by panqingrong on 29/09/2016.
 */
@Controller
@RequestMapping("/data/dashboard")
public class DashBoardViewController {
    @RequestMapping("show")
    public String showDashBoard(Model model){
        return "data/DashBoard";
    }
}
