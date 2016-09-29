/**
 * 
 */
package abraham.web.service.security.reception;

import java.util.List;
import java.util.Set;

import abraham.web.service.security.reception.models.ResourcesGroup;
import abraham.web.service.security.reception.models.SecuredResource;
import pan.utils.AppBizException;

/**
 * @author panqingrong
 *
 */
public interface SecurityReceptionService {
	
	List<ResourcesGroup> getResourceGroup(Long userSid) throws AppBizException;
	
	Set<SecuredResource> getSecuredResources(Long groupSid) throws AppBizException;
	

}
