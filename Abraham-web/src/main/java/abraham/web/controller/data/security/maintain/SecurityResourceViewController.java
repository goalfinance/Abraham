package abraham.web.controller.data.security.maintain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data/security/maintain/resource")
public class SecurityResourceViewController {

    @RequestMapping("main_view")
    public String mainView(){
        return "data/security/maintain/resources/MainView";
    }
}
