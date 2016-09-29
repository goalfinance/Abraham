/**
 * 
 */
package abraham.web.controller.common;

import abraham.web.service.security.reception.SecurityReceptionService;
import abraham.web.service.security.reception.models.ResourcesGroup;
import abraham.web.shiro.ApplicationRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pan.utils.AppBizException;

import java.util.List;

/**
 * @author panqingrong
 *
 */
@Controller
@RequestMapping("/common/decorator")
public class DecoratorViewController {
	@Autowired
	private SecurityReceptionService securityReceptionService;

	@RequestMapping("default")
	public String defaultView(){
		return "common/DefaultDecorator";
	}

	@RequestMapping("app_frame")
	public String appFrameView(Model model) {
		List<ResourcesGroup> groups = null;
		Long userSid = -1L;
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated() == true) {
			ApplicationRealm.ShiroUser shiroUser = (ApplicationRealm.ShiroUser) currentUser.getPrincipals().getPrimaryPrincipal();
			userSid = Long.valueOf(shiroUser.getUsersid());

			model.addAttribute("nickname", shiroUser.getNickname());
			model.addAttribute("userSid", shiroUser.getUsersid());
		}
		try {
			groups = securityReceptionService.getResourceGroup(userSid);
			for (ResourcesGroup rg : groups) {
				rg.setResources(securityReceptionService.getSecuredResources(Long.valueOf(rg.getGid())));
			}
			model.addAttribute("groups", groups);
		} catch (AppBizException e) {

		}
		return "common/AppFrameDecorator";
	}
}
