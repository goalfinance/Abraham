/**
 *
 */
package abraham.web.service.security.reception;

import abraham.core.web.security.domain.SecurityResource;
import abraham.core.web.security.domain.SecurityResourceGroup;
import abraham.core.web.security.service.SecurityResourceService;
import abraham.web.service.security.reception.models.ResourcesGroup;
import abraham.web.service.security.reception.models.SecuredResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pan.utils.AppBizException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author panqingrong
 */
@Component
public class SecurityReceptionServiceImpl implements SecurityReceptionService {
    private final static String WELCOME_GROUP_ID = "9999";

    @Autowired(required = false)
    private SecurityResourceService securityResourceService;

    /* (non-Javadoc)
     * @see pan.mas.console.output.web.service.security.reception.SecurityReceptionService#getResourceGroup(java.lang.Long)
     */
    @Override
    public List<ResourcesGroup> getResourceGroup(Long userSid) throws AppBizException {
        List<ResourcesGroup> groups = new ArrayList<ResourcesGroup>();
        if (userSid < 0) {
            //It's a unauthenticated user.
            ResourcesGroup rgDefault = new ResourcesGroup();
            rgDefault.setName("Welcome Visitor!");
            rgDefault.setGid(WELCOME_GROUP_ID);
            groups.add(rgDefault);
        } else {
            //It's an authenticated user.
            List<SecurityResourceGroup> srgs = securityResourceService.findSecurityResourceGroupByUserSid(userSid);

            for (SecurityResourceGroup srg : srgs) {
                ResourcesGroup rg = new ResourcesGroup();
                rg.setGid(srg.getSid().toString());
                rg.setName(srg.getName());
                groups.add(rg);
            }
        }
        return groups;
    }

    /* (non-Javadoc)
     * @see pan.mas.console.output.web.service.security.reception.SecurityReceptionService#getSecurityResources(java.lang.Long)
     */
    @Override
    public Set<SecuredResource> getSecuredResources(Long groupSid) throws AppBizException {
        Set<SecuredResource> resources = new HashSet<SecuredResource>();
        List<SecurityResource> srs = securityResourceService.findSecurityResourceByGroupSid(groupSid);
        if (srs.isEmpty()) {
            return resources;
        } else {
            for (SecurityResource sr : srs) {
                SecuredResource securedResource = new SecuredResource();
                securedResource.setRid(sr.getSid().toString());
                securedResource.setName(sr.getName());
                securedResource.setRlocation(sr.getLocation());

                resources.add(securedResource);
            }

            return resources;
        }

    }

}
