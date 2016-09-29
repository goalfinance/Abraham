package abraham.web.controller.common.reception;
/**
 * 
 */


import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import abraham.web.service.security.reception.SecurityReceptionService;
import abraham.web.service.security.reception.models.LoginBean;
import abraham.web.service.security.reception.models.ResourcesGroup;
import abraham.web.shiro.ApplicationRealm.ShiroUser;
import pan.utils.AppBizException;

/**
 * @author panqingrong
 *
 */
@Controller
@RequestMapping("/common")
public class SecurityReceptionViewController {

	@Autowired
	private SecurityReceptionService securityReceptionService;

	
	@RequestMapping("show_login")
	public String showLogin(Model model){
		LoginBean loginBean = new LoginBean();
		model.addAttribute("login", loginBean);
		
		return "common/Login";
	}



}
