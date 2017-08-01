/**
 *
 */
package abraham.web.restcontroller.security.maintaining;

import abraham.core.web.security.domain.SecurityUser;
import abraham.core.web.security.service.WebSecurityService;
import abraham.web.restcontroller.security.maintaining.beans.ChangePasswordBean;
import abraham.web.restcontroller.security.maintaining.beans.SecurityUserBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pan.utils.AppBizException;
import pan.utils.AppExceptionCodes;
import pan.utils.BeanValidator;
import pan.utils.data.Order;
import pan.utils.web.MediaTypes;
import pan.utils.web.datatables.DTQueryPagination;
import pan.utils.web.datatables.DTQueryResultPagination;
import pan.utils.web.datatables.DataTablesUtils;

import java.util.Date;
import java.util.List;

/**
 * @author panqingrong
 */
@RestController
@RequestMapping("restapis/security/maintaining")
public class SecurityMaintainingRestController {
    private final Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private WebSecurityService webSecurityService;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registerSecurityUser(@RequestBody SecurityUserBean securityUser) throws AppBizException {
        BeanValidator.validate(securityUser);
        if (!securityUser.getPasswd().equals(securityUser.getPasswdConfirmed())) {
            throw new AppBizException(AppExceptionCodes.SEC_PASSWORD_INPUT_TWICE_NOT_MATCHED[0], AppExceptionCodes.SEC_PASSWORD_INPUT_TWICE_NOT_MATCHED[1]);
        }

        SecurityUser su = new SecurityUser();
        su.setUserId(securityUser.getUserId());
        su.setFullName(securityUser.getFullName());
        su.setNickName(securityUser.getNickName());
        su.setPlainPasswd(securityUser.getPasswd());

        webSecurityService.registerSecurityUser(su);
    }

    @RequestMapping(value = "/user/change_password/{sId}", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeSecurityUserPassword(@PathVariable Long sId, @RequestBody ChangePasswordBean cp) throws AppBizException {
        log.debug("Change the security user[sid'" + sId + "']'s password");
        webSecurityService.changePassword(sId, cp.getOldPassword(), cp.getNewPassword());
    }

    @RequestMapping(value = "user/{sId}", method = RequestMethod.PUT, consumes = MediaTypes.JSON_UTF_8)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSecurityUser(@PathVariable("sId") Long sId, @RequestBody SecurityUser su) throws AppBizException {
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
    public DTQueryResultPagination<SecurityUser> listSecurityUsers(@RequestBody DTQueryPagination dtQuery) {
        DataTablesUtils<SecurityUser> dtUtils = new DataTablesUtils<SecurityUser>();
        int pageNum = dtUtils.calcPageNumber(dtQuery);
        List<Order> orders = dtUtils.getOrders(dtQuery);
        Page<SecurityUser> sus = webSecurityService.findSecurityUserByUserId(null, pageNum, dtQuery.getLength(), orders);
        DTQueryResultPagination<SecurityUser> queryResult = dtUtils.convertDataTablesQueryResult(dtQuery.getDraw(), sus);
        return queryResult;
    }
}
