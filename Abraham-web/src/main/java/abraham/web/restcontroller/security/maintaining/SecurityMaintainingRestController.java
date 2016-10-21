/**
 * 
 */
package abraham.web.restcontroller.security.maintaining;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import abraham.core.web.security.domain.SecurityUser;
import abraham.core.web.security.service.WebSecurityService;
import pan.utils.web.datatables.*;
import abraham.web.restcontroller.security.maintaining.beans.ChangePasswordBean;
import pan.utils.AppBizException;
import pan.utils.data.Order;
import pan.utils.web.MediaTypes;

/**
 * @author panqingrong
 *
 */
@RestController
@RequestMapping("restapis/security/maintaining")
public class SecurityMaintainingRestController {
	private final Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private WebSecurityService webSecurityService;

	@RequestMapping(value = "/user/change_password/{sId}", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changePassword(@PathVariable Long sId, @RequestBody ChangePasswordBean cp) throws AppBizException {
		log.debug("Change the security user[sid'" + sId + "']'s password");
		webSecurityService.changePassword(sId, cp.getOldPassword(), cp.getNewPassword());
	}

	@RequestMapping(value = "user/{sId}", method = RequestMethod.PUT, consumes = MediaTypes.JSON_UTF_8)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("sId") Long sId, @RequestBody SecurityUser su) throws AppBizException {
		log.debug("Update the security user[sid='" + sId + "']");
		SecurityUser suFromDb = null;

		suFromDb = webSecurityService.findSecurityUserBySid(sId);

		if (su.getFullName() != null && !su.getFullName().equals("")) {
			suFromDb.setFullName(su.getFullName());
		}
		if (su.getNickName() != null && !su.getNickName().equals("")) {
			suFromDb.setNickName(su.getNickName());
		}
		if (su.getRoles() != null && !su.getRoles().equals("")) {
			suFromDb.setRoles(su.getRoles());
		}
		suFromDb.setUpdateTime(new Date());
		webSecurityService.saveSecurityUser(suFromDb);

		log.debug("Update the security user[sid='" + sId + "'] --success");
	}

	@RequestMapping(value = "user/list", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8, produces = MediaTypes.JSON_UTF_8)
	public DTQueryResultPagination<SecurityUser> list(@RequestBody DTQueryPagination dtQuery) {
		DataTablesUtils<SecurityUser> dtUtils = new DataTablesUtils<SecurityUser>();
		int pageNum = dtUtils.calcPageNumber(dtQuery);
		List<Order> orders = dtUtils.getOrders(dtQuery);
		Page<SecurityUser> sus = webSecurityService.findSecurityUserByUserId(null, pageNum, dtQuery.getLength(), orders);
		DTQueryResultPagination<SecurityUser> queryResult = dtUtils.convertDataTablesQueryResult(dtQuery.getDraw(), sus);
		return queryResult;
	}
}
