package abraham.web.restcontroller.keymanager;

import abraham.core.ca.domain.PrivateKeyInfo;
import abraham.core.ca.service.PrivateKeyService;
import abraham.web.restcontroller.security.common.beans.DTQueryColumn;
import abraham.web.restcontroller.security.common.beans.DTQueryOrder;
import abraham.web.restcontroller.security.common.beans.DTQueryPagination;
import abraham.web.restcontroller.security.common.beans.DTQueryResultPagination;
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
        int pageNum = (dtQuery.getStart() / dtQuery.getLength());
        List<Order> orders = new ArrayList<Order>();
        for (DTQueryOrder dqo: dtQuery.getOrder()){
            DTQueryColumn dqColumn = dtQuery.getColumns().get(dqo.getColumn());
            orders.add(new Order(dqo.getDir(), dqColumn.getData()));
        }
        Page<PrivateKeyInfo>  pkInfos = privateKeyService.findAllPrivateKeyInfo(pageNum, dtQuery.getLength(), orders);
        DTQueryResultPagination<PrivateKeyInfo> queryResult = new DTQueryResultPagination<PrivateKeyInfo>();
        queryResult.setData(pkInfos.getContent());
        queryResult.setDraw(dtQuery.getDraw());
        queryResult.setRecordsTotal(pkInfos.getTotalElements());
        queryResult.setRecordsFiltered(pkInfos.getTotalElements());
        return queryResult;
    }
}
