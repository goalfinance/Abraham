/**
 *
 */
package abraham.core.web.security.service;

import abraham.core.web.security.domain.*;
import abraham.core.web.security.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pan.utils.AppBizException;
import pan.utils.AppExceptionCodes;
import pan.utils.data.Order;
import pan.utils.security.shiro.CredentialsService;

import java.util.*;

/**
 * @author panqingrong
 */
@Component
public class WebSecurityServiceImpl implements WebSecurityService {

    @Autowired
    private SecurityUserRepository securityUserRepository;

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private SecurityRoleRepository securityRoleRepository;

    @Autowired
    private SecurityUserPermissionRepository securityUserPermissionRepository;

    @Autowired
    private SecurityRolePermissionRepository securityRolePermissionRepository;

    @Autowired
    private SecurityResourceRepository securityResourceReponsitory;

    private void encryptPassword(SecurityUser securityUser) throws AppBizException {
        securityUser.setSalt(credentialsService.generateSalt());
        securityUser
                .setPasswd(credentialsService.encryptPassword(securityUser.getPlainPasswd(), securityUser.getSalt()));
    }

    /*
     * (non-Javadoc)
     *
     * @see pan.mas.console.core.outpost.web.security.service.
     * OutpostWebSecurityService#findSecurityUserInfoByUserId(java.lang.String)
     */
    public SecurityUser findSecurityUserInfoByUserId(String userId) throws AppBizException {
        SecurityUser su = securityUserRepository.findByUserId(userId);
        if (su == null) {
            Object[] args = new Object[1];
            args[0] = userId;
            throw new AppBizException(AppExceptionCodes.SEC_USER_DOES_NOT_EXIST[0],
                    AppExceptionCodes.SEC_USER_DOES_NOT_EXIST[1], args);
        } else {
            return su;
        }
    }

    public List<SecurityUserPermission> findPermisionByUserSid(Long userSid) throws AppBizException {
        return securityUserPermissionRepository.findByUserSid(userSid);
    }

    public List<SecurityRolePermission> findPermissionByRoleSid(Long roleSid) throws AppBizException {
        return securityRolePermissionRepository.findByRoleSid(roleSid);
    }

    public Page<SecurityUser> findSecurityUserByUserId(String userId, int pageNumber, int pageSize,
                                                       List<Order> orders) {
        if (userId == null) {
            userId = "";
        }
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Order.newSort(orders));

        return securityUserRepository.findByUserIdLike(userId, pageRequest);
    }

    public void registerSecurityUser(SecurityUser securityUser) throws AppBizException {
        encryptPassword(securityUser);
        securityUser.setCreateTime(new Date());
        securityUserRepository.save(securityUser);
    }

    public SecurityUser findSecurityUserBySid(Long sid) throws AppBizException {
        Optional<SecurityUser> result = securityUserRepository.findById(sid);
        return result.orElseThrow(()->{
            Object[] args = new Object[1];
            args[0] = sid;
            return new AppBizException(AppExceptionCodes.SEC_USER_DOES_NOT_EXIST[0],
                    AppExceptionCodes.SEC_USER_DOES_NOT_EXIST[1], args);
        });

    }

    public List<SecurityRole> findAllSecurityRoles() {
        Iterator<SecurityRole> securityRoleIterator = securityRoleRepository.findAll().iterator();
        List<SecurityRole> securityRoles = new ArrayList<SecurityRole>();
        while (securityRoleIterator.hasNext()) {
            SecurityRole securityRole = securityRoleIterator.next();
            securityRoles.add(securityRole);
        }
        return securityRoles;
    }

    public List<SecurityRole> findAllActiveSecurityRoles() {
        Iterator<SecurityRole> securityRoleIterator = securityRoleRepository.findAll().iterator();
        List<SecurityRole> securityRoles = new ArrayList<SecurityRole>();
        while (securityRoleIterator.hasNext()) {
            SecurityRole securityRole = securityRoleIterator.next();
            if (securityRole.getStatus().equals(SecurityRole.STATUS_ACTIVE)) {
                securityRoles.add(securityRole);
            }
        }
        return securityRoles;
    }

    public void deleteSecurityUser(SecurityUser su) {
        securityUserRepository.delete(su);
    }

    public void saveSecurityUser(SecurityUser su) throws AppBizException {
        securityUserRepository.save(su);
    }

    public void changePassword(Long sid, String oldOne, String newOne) throws AppBizException {
        // First of all, check if the old password can be matched.
        SecurityUser su = this.findSecurityUserBySid(sid);
        String oldPwdHashed = credentialsService.encryptPassword(oldOne, su.getSalt());
        if (!oldPwdHashed.equalsIgnoreCase(su.getPasswd())) {
            throw new AppBizException(AppExceptionCodes.SEC_NOT_MATCH_OLDPASSWORD[0],
                    AppExceptionCodes.SEC_NOT_MATCH_OLDPASSWORD[1]);
        }

        // And second, update the new one's info.
        su.setSalt(credentialsService.generateSalt());
        su.setPasswd(credentialsService.encryptPassword(newOne, su.getSalt()));
        su.setPwdChangeTime(new Date());

        securityUserRepository.save(su);
    }

    public void saveSecurityRole(SecurityRole securityRole) throws AppBizException {
        securityRoleRepository.save(securityRole);
    }

    public void deleteSecurityRole(SecurityRole securityRole) {
        securityRoleRepository.delete(securityRole);
    }

    public SecurityRole findSecurityRoleBySid(Long sid) throws AppBizException {
        return securityRoleRepository.findById(sid).orElse(null);
    }

    @Transactional(rollbackFor = AppBizException.class)
    public void saveSecurityRolePermissions(Long roleSid, Set<Long> resourcesPermitted, String permittedAction)
            throws AppBizException {
        securityRolePermissionRepository.deleteRolesPermission(roleSid);
        for (Long resourcePermittedId : resourcesPermitted) {
            SecurityRolePermission srp = new SecurityRolePermission();
            Optional<SecurityResource> result = securityResourceReponsitory.findById(resourcePermittedId);
            srp.setRoleSid(roleSid);
            srp.setResourceGroupSid(result.map(securityResource -> securityResource.getGroupSid()).orElse(null));
            srp.setPermission(resourcePermittedId + ":" + permittedAction);
            srp.setResourceSid(resourcePermittedId);
            srp.setStatus(SecurityRolePermission.STATUS_ACTIVE);
            srp.setCreatetime(new Date());
            securityRolePermissionRepository.save(srp);
        }
    }

    public void deleteSecurityRolePermission(SecurityRolePermission securityRolePermission) {
        securityRolePermissionRepository.delete(securityRolePermission);
    }

    public SecurityRolePermission findSecurityRolePermissionBySid(Long sid) throws AppBizException {
        return securityRolePermissionRepository.findById(sid).orElse(null);
    }

}
