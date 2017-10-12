/**
 *
 */
package abraham.web.shiro;

import abraham.core.web.security.domain.SecurityUser;
import abraham.core.web.security.domain.SecurityUserPermission;
import abraham.core.web.security.service.WebSecurityService;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import pan.utils.AppBizException;
import pan.utils.Encodes;
import pan.utils.security.shiro.CredentialsService;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static pan.utils.Encodes.decodeHex;

/**
 * @author panqingrong
 */
public class ApplicationRealm extends AuthorizingRealm {
    private Log log = LogFactory.getLog(this.getClass());

    private WebSecurityService webSecurityService;

    private CredentialsService credentialsService;

    private PermissionResolver permissionResolver = new WildcardPermissionResolver();

    public ApplicationRealm(WebSecurityService outpostWebSecurityService,
                            CredentialsService credentialsService) {
        super(credentialsService.getCredentialsMatcher());
        this.webSecurityService = outpostWebSecurityService;
        this.credentialsService = credentialsService;
    }

    @Override
    protected void onInit() {
        super.onInit();
        Validate.notNull(this.webSecurityService, "Service[WebSecurityService] must not be null.");
        Validate.notNull(this.credentialsService, "Service[CredentialsService] must not be null.");
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache
     * .shiro.subject.PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        ShiroUser sUser = (ShiroUser) principals.getPrimaryPrincipal();
        info.addObjectPermissions(resolveUserPermission(sUser));
        info.addRoles(resolveUserRoles(sUser));
        return info;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.
     * apache.shiro.authc.AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();

        try {
            SecurityUser su = this.webSecurityService.findSecurityUserInfoByUserId(username);
            byte[] salt = decodeHex(su.getSalt());
            return new SimpleAuthenticationInfo(createShiroUser(su), su.getPasswd(), ByteSource.Util.bytes(salt),
                    getName());
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage(), e);
        }
    }

    protected ShiroUser createShiroUser(SecurityUser su) {

        ShiroUser sUser = new ShiroUser();

        sUser.setFullname(su.getFullName());
        sUser.setNickname(su.getNickName());
        sUser.setUserid(su.getUserId());
        sUser.setUsersid(su.getSId().toString());
        sUser.setRoles(su.getRolesSet());

        return sUser;
    }

    private Set<Permission> resolveUserPermission(ShiroUser sUser) {
        Long userSid = Long.valueOf(sUser.getUsersid());

        Set<Permission> userPermission = new HashSet<Permission>();
        try {
            List<SecurityUserPermission> sups = webSecurityService.findPermisionByUserSid(userSid);

            for (SecurityUserPermission sup : sups) {
                Permission p = permissionResolver.resolvePermission(sup.getPermission());
                userPermission.add(p);
            }
        } catch (AppBizException e) {
            log.error(e.getMessage(), e);
        }
        return userPermission;
    }

    private Set<String> resolveUserRoles(ShiroUser sUser) {
        return sUser.getRoles();
    }

    public WebSecurityService getOutpostWebSecurityService() {
        return webSecurityService;
    }

    public void setOutpostWebSecurityService(WebSecurityService outpostWebSecurityService) {
        this.webSecurityService = outpostWebSecurityService;
    }

    public static class ShiroUser implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 4005700659499005021L;
        private String usersid;
        private String userid;
        private String fullname;
        private String nickname;
        private Set<String> roles;

        public String getUsersid() {
            return usersid;
        }

        public void setUsersid(String usersid) {
            this.usersid = usersid;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Set<String> getRoles() {
            return roles;
        }

        public void setRoles(Set<String> roles) {
            this.roles = roles;
        }

    }

}
