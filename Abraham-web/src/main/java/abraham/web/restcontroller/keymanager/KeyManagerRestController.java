package abraham.web.restcontroller.keymanager;

import abraham.core.ca.domain.PrivateKeyInfo;
import abraham.core.ca.service.PrivateKeyService;
import pan.utils.web.datatables.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pan.utils.data.Order;
import pan.utils.web.MediaTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panqingrong on 20/10/2016.
 */
@RestController
@RequestMapping("restapis/keymanager")
public class KeyManagerRestController {
    @Autowired
    private PrivateKeyService privateKeyService;

    @RequestMapping(value="privatekey/list",
            method= RequestMethod.POST,
            consumes=MediaTypes.JSON_UTF_8, produces=MediaTypes.JSON_UTF_8)
    public DTQueryResultPagination<PrivateKeyInfo> listPrivateKeys(@RequestBody DTQueryPagination dtQuery){
        DataTablesUtils<PrivateKeyInfo> dtUtils = new DataTablesUtils<PrivateKeyInfo>();
        int pageNum = dtUtils.calcPageNumber(dtQuery);
        List<Order> orders = dtUtils.getOrders(dtQuery);
        Page<PrivateKeyInfo>  pkInfos = privateKeyService.findAllPrivateKeyInfo(pageNum, dtQuery.getLength(), orders);
        DTQueryResultPagination<PrivateKeyInfo> queryResult = dtUtils.convertDataTablesQueryResult(dtQuery.getDraw(), pkInfos);
        return queryResult;
    }
}
