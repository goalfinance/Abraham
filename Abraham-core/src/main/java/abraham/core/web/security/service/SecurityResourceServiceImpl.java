/**
 *
 */
package abraham.core.web.security.service;

import abraham.core.web.security.domain.SecurityResource;
import abraham.core.web.security.domain.SecurityResourceGroup;
import abraham.core.web.security.domain.SecurityRolePermission;
import abraham.core.web.security.domain.SecurityUser;
import abraham.core.web.security.repository.SecurityResourceGroupRepository;
import abraham.core.web.security.repository.SecurityResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pan.utils.AppBizException;
import pan.utils.AppExceptionCodes;
import pan.utils.AppRTException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author panqingrong
 */
@Component
public class SecurityResourceServiceImpl implements SecurityResourceService {

    @Autowired
    private SecurityResourceGroupRepository securityResourceGroupRepository;

    @Autowired
    private SecurityResourceRepository securityResourceRepository;

    @Autowired
    private WebSecurityService webSecurityService;

    /* (non-Javadoc)
     * @see pan.mas.console.core.outpost.web.security.service.SecurityResourceService#findAllSecurityResourceGroup()
     */
    public List<SecurityResourceGroup> findAllSecurityResourceGroup() throws AppBizException {
        return securityResourceGroupRepository.findAllOrderBySortIdx();
    }

    /* (non-Javadoc)
     * @see pan.mas.console.core.outpost.web.security.service.SecurityResourceService#findSecurityResourceByGroupSid(java.lang.Long)
     */
    public List<SecurityResource> findSecurityResourceByGroupSid(Long groupSid) throws AppBizException {
        return securityResourceRepository.findByGroupSidOrderBySortIdx(groupSid);
    }

    public SecurityResource findSecurityResourceBySid(Long resourceSid) throws AppBizException{
        return securityResourceRepository.findById(resourceSid).orElseThrow(()->{
            Object[] args = new Object[1];
            args[0] = resourceSid;
            return new AppBizException(AppExceptionCodes.SEC_SECURITY_RESOURCE_DOES_NOT_EXIST[0],
                    AppExceptionCodes.SEC_SECURITY_RESOURCE_DOES_NOT_EXIST[1]);
        });
    }

    public List<SecurityResourceGroup> findSecurityResourceGroupByUserSid(Long userSid) throws AppBizException {
        //First, I get the security user's all roles assigned.
        SecurityUser securityUser = webSecurityService.findSecurityUserBySid(userSid);
        Set<String> roleSet = securityUser.getRolesSet();

        //Second, I get the info of all permissions belong to roles.
        List<SecurityRolePermission> resourcesPermitted = new ArrayList<SecurityRolePermission>();
        for (String roleSid : roleSet) {
            Long sid = Long.valueOf(roleSid);
            List<SecurityRolePermission> srps = webSecurityService.findPermissionByRoleSid(sid);

            resourcesPermitted.addAll(srps);
        }

        //The third, I get all resources group's sids that are not repeatable from the permissions assigned to the security user.
        List<Long> resourceGroupPermitted = new ArrayList<Long>();
        for (SecurityRolePermission srp : resourcesPermitted) {
            if (!resourceGroupPermitted.contains(srp.getResourceGroupSid().longValue())) {
                resourceGroupPermitted.add(srp.getResourceGroupSid().longValue());
            }
        }

        //Finally, I get all resources groups' infos that are sorted.
        return securityResourceGroupRepository.findAllBySidInOrderBySortIdxAsc(resourceGroupPermitted);
    }

    private List<SecurityResourceGroup> sortIdxMoveDown(List<SecurityResourceGroup>  srgs){
        return srgs.stream().map(securityResourceGroup -> {
            securityResourceGroup.setSortIdx(securityResourceGroup.getSortIdx() + 1);
            return securityResourceGroup;
        }).collect(Collectors.toList());
    }

    private Long saveBeforeGroupSortIdx(SecurityResourceGroup newSecurityResourceGroup, int beforeGroupSortIdx) {
        List<SecurityResourceGroup> results = securityResourceGroupRepository.findBySortIdxGreaterThanEqualOrderBySortIdxAsc(beforeGroupSortIdx).orElse(new ArrayList<SecurityResourceGroup>());
        List<SecurityResourceGroup> movedDownList = this.sortIdxMoveDown(results);
        securityResourceGroupRepository.saveAll(movedDownList);

        newSecurityResourceGroup.setSortIdx(beforeGroupSortIdx);
        SecurityResourceGroup savedOne = securityResourceGroupRepository.save(newSecurityResourceGroup);

        return savedOne.getSid();
    }

    @Override
    @Transactional
    public Long addSecurityResourceGroupBefore(Long beforeGroupSid, SecurityResourceGroup newSecurityResourceGroup) throws AppBizException {
        SecurityResourceGroup srg = securityResourceGroupRepository.findById(beforeGroupSid).orElseThrow(()->{
            Object[] args = new Object[1];
            args[0] = beforeGroupSid;
            return new AppBizException(AppExceptionCodes.SEC_SECURITY_RESOURCE_GROUP_DOES_NOT_EXIST[0], AppExceptionCodes.SEC_SECURITY_RESOURCE_GROUP_DOES_NOT_EXIST[1]);
        });
        int beforeGroupSortIdx = srg.getSortIdx();
        return saveBeforeGroupSortIdx(newSecurityResourceGroup, beforeGroupSortIdx);
    }

    @Override
    @Transactional
    public Long addSecurityResourceGroupAfter(Long afterGroupSid, SecurityResourceGroup newSecurityResourceGroup) throws AppBizException {
        SecurityResourceGroup srg = securityResourceGroupRepository.findById(afterGroupSid).orElseThrow(()->{
            Object[] args = new Object[1];
            args[0] = afterGroupSid;
            return new AppBizException(AppExceptionCodes.SEC_SECURITY_RESOURCE_GROUP_DOES_NOT_EXIST[0], AppExceptionCodes.SEC_SECURITY_RESOURCE_GROUP_DOES_NOT_EXIST[1]);
        });

        int beforeGroupSortIdx = srg.getSortIdx() + 1;
        return saveBeforeGroupSortIdx(newSecurityResourceGroup, beforeGroupSortIdx);
    }

    @Override
    @Transactional
    public Long addSecurityResourceGroupFirst(SecurityResourceGroup securityResourceGroup) throws AppBizException {
        return saveBeforeGroupSortIdx(securityResourceGroup, 0);
    }

    @Override
    @Transactional
    public Long addSecurityResourceGroupLast(SecurityResourceGroup securityResourceGroup) throws AppBizException {
        int beforeGroupSortIdx = securityResourceGroupRepository.getMaxGroupSortIdx() + 1;
        return saveBeforeGroupSortIdx(securityResourceGroup, beforeGroupSortIdx);
    }
}
