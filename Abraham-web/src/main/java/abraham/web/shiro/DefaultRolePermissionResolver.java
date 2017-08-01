/**
 *
 */
package abraham.web.shiro;

import abraham.core.web.security.domain.SecurityRolePermission;
import abraham.core.web.security.service.WebSecurityService;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import pan.utils.AppBizException;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author panqingrong
 */
public class DefaultRolePermissionResolver implements RolePermissionResolver {
    private Log log = LogFactory.getLog(this.getClass());

    private WebSecurityService webSecurityService;

    private PermissionResolver permissionResolver = new WildcardPermissionResolver();


    public DefaultRolePermissionResolver(WebSecurityService securityService) {
        super();
        Validate.notNull(securityService, "Serivce[WebSecurityService] must not null");
        this.webSecurityService = securityService;
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.authz.permission.RolePermissionResolver#resolvePermissionsInRole(java.lang.String)
     */
    @Override
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        Long roleSid = Long.valueOf(roleString);
        Set<Permission> rolePermission = new HashSet<Permission>();
        try {
            List<SecurityRolePermission> srps = webSecurityService.findPermissionByRoleSid(roleSid);

            for (SecurityRolePermission srp : srps) {
                Permission p = permissionResolver.resolvePermission(srp.getPermission());
                rolePermission.add(p);
            }
        } catch (AppBizException e) {
            log.error(e.getMessage(), e);
        }
        return rolePermission;
    }

    public WebSecurityService getSecurityService() {
        return webSecurityService;
    }

    public void setSecurityService(WebSecurityService securityService) {
        this.webSecurityService = securityService;
    }

}
