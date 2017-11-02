/**
 *
 */
package abraham.core.web.security.service;

import abraham.core.web.security.domain.SecurityResource;
import abraham.core.web.security.domain.SecurityResourceGroup;
import pan.utils.AppBizException;

import java.util.List;

/**
 * @author panqingrong
 */
public interface SecurityResourceService {

    public List<SecurityResourceGroup> findAllSecurityResourceGroup() throws AppBizException;

    public List<SecurityResource> findSecurityResourceByGroupSid(Long groupSid) throws AppBizException;

    public SecurityResource findSecurityResourceBySid(Long resourceSid) throws AppBizException;

    public List<SecurityResourceGroup> findSecurityResourceGroupByUserSid(Long userSid) throws AppBizException;

    public Long addSecurityResourceGroupBefore(Long beforeGroupSid, SecurityResourceGroup newSecurityResourceGroup) throws AppBizException;

    public Long addSecurityResourceGroupAfter(Long afterGroupSid, SecurityResourceGroup newSecurityResourceGroup) throws AppBizException;

    public Long addSecurityResourceGroupFirst(SecurityResourceGroup securityResourceGroup) throws AppBizException;

    public Long addSecurityResourceGroupLast(SecurityResourceGroup securityResourceGroup) throws AppBizException;

}
