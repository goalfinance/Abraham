/**
 * 
 */
package abraham.web.controller.data.security.maintain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import abraham.core.web.security.domain.SecurityUser;
import abraham.core.web.security.service.WebSecurityService;
import pan.utils.AppBizException;

/**
 * @author panqingrong
 *
 */
@Controller
@RequestMapping("/data/security/maintain/user")
public class SecurityUserViewController {
	@Autowired
	private WebSecurityService webSecurityService;
	
	@RequestMapping("show_profile")
	public String showProfile(@RequestParam(name="sId", required=true)String sId, Model model){
		try {
			SecurityUser securityUser = webSecurityService.findSecurityUserBySid(Long.valueOf(sId));
			model.addAttribute("securityUser", securityUser);
		} catch (NumberFormatException e) {
			model.addAttribute("errorFlag", true);
			model.addAttribute("errorMessage", "Error when converting '" + sId + "' to 'Long' type.");
		} catch (AppBizException e) { 
			model.addAttribute("errorFlag", true);
			model.addAttribute("errorMessage", e.getTextMessage());
		}
		return "data/security/maintain/ShowUsersProfile";
	}
	
	@RequestMapping("change_password_modal")
	public String changePassword(@RequestParam(name="sId", required=true)String sId, Model model){
		model.addAttribute("userSid", sId);
		return "data/security/maintain/ChangePasswordModal";
	}
	
	@RequestMapping("main_view")
	public String mainView(){
		return "data/security/maintain/UserMainView";
	}

}
