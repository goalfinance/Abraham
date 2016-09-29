/**
 * 
 */
package abraham.core.web.security.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import abraham.core.web.security.domain.SecurityRole;
import abraham.core.web.security.domain.SecurityRolePermission;
import abraham.core.web.security.domain.SecurityUser;
import abraham.core.web.security.domain.SecurityUserPermission;
import pan.utils.AppBizException;
import pan.utils.data.Order;

/**
 * @author panqingrong
 *
 */
public interface WebSecurityService {
	
	public SecurityUser findSecurityUserInfoByUserId(String userId) throws AppBizException;
	
	/**
	 * 
	 * @param userId
	 * @param pageNumber zero-based page index.
	 * @param pageSize
	 * @return
	 */
	public Page<SecurityUser> findSecurityUserByUserId(String userId, int pageNumber, int pageSize, List<Order> orders);
	
	public void registerSecurityUser(SecurityUser securityUser) throws AppBizException;
	
	public SecurityUser findSecurityUserBySid(Long sid) throws AppBizException;
	
	public void saveSecurityUser(SecurityUser su) throws AppBizException;
	
	public void deleteSecurityUser(SecurityUser su);
	
	public void changePassword(Long sid, String oldOne, String newOne) throws AppBizException;
	
	public List<SecurityRole> findAllSecurityRoles();
	
	public List<SecurityRole> findAllActiveSecurityRoles();
	
	public void saveSecurityRole(SecurityRole securityRole) throws AppBizException;
	
	public void deleteSecurityRole(SecurityRole securityRole);
	
	public SecurityRole findSecurityRoleBySid(Long sid) throws AppBizException;
	
	public List<SecurityUserPermission> findPermisionByUserSid(Long userSid) throws AppBizException;
	
	public List<SecurityRolePermission> findPermissionByRoleSid(Long roleSid) throws AppBizException;
	
	public void saveSecurityRolePermissions(Long roleSid, Set<Long> resourcesPermitted, String permittedAction) throws AppBizException;
	
	public void deleteSecurityRolePermission(SecurityRolePermission securityRolePermission);
	
	public SecurityRolePermission findSecurityRolePermissionBySid(Long sid) throws AppBizException;
}
