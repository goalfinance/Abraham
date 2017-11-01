package abraham.web.restcontroller.security.maintaining;

import abraham.core.web.security.domain.SecurityUser;
import abraham.core.web.security.service.SecurityResourceService;
import abraham.web.restcontroller.security.maintaining.beans.SecurityResourceBean;
import abraham.web.restcontroller.security.maintaining.beans.SecurityResourceGroupBean4JSTree;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pan.utils.AppBizException;
import pan.utils.web.MediaTypes;
import pan.utils.web.datatables.DTQueryPagination;
import pan.utils.web.datatables.DTQueryResultPagination;
import pan.utils.web.datatables.DataTablesUtils;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("restapis/security/maintainting/resource")
public class SecurityResourceMaintainingRestController {
    private final Log log = LogFactory.getLog(this.getClass());
    @Autowired
    private SecurityResourceService securityResourceService;

    @RequestMapping(value = "group/list",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
    public List<SecurityResourceGroupBean4JSTree> allResourceGroups() throws AppBizException{
        return securityResourceService.findAllSecurityResourceGroup().stream().map(securityResourceGroup -> {
            SecurityResourceGroupBean4JSTree bean = new SecurityResourceGroupBean4JSTree();
            bean.setId(securityResourceGroup.getSid());
            bean.setText(securityResourceGroup.getName());
            bean.setParent("#");
            return bean;
        }).collect(Collectors.toList());
    }

    @RequestMapping(value="bygroupsid/{groupSid}",method=RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8, produces = MediaTypes.JSON_UTF_8)
    public DTQueryResultPagination<SecurityResourceBean> resourcesByGroupId(@PathVariable("groupSid") Long groupSid,
                                                                            @RequestBody DTQueryPagination dtQuery) throws AppBizException{
        List<SecurityResourceBean> data = securityResourceService.findSecurityResourceByGroupSid(groupSid).stream().map(securityResource -> {
            SecurityResourceBean bean = new SecurityResourceBean();
            bean.setName(securityResource.getName());
            bean.setDescription(securityResource.getDescription());
            bean.setGroupSid(securityResource.getGroupSid());
            bean.setStatus(securityResource.getStatus());
            return bean;
        }).collect(Collectors.toList());
        DataTablesUtils<SecurityResourceBean> dtUtils = new DataTablesUtils<SecurityResourceBean>();
        DTQueryResultPagination<SecurityResourceBean> queryResult = dtUtils.convertDataTablesQueryResult(dtQuery.getDraw(), data);

        return queryResult;
    }
}
