package abraham.web.restcontroller.keymanager;

import abraham.core.ca.domain.KeyPairInfo;
import abraham.core.ca.service.KeyPairService;
import abraham.core.ca.service.VOKeyPair;
import abraham.web.restcontroller.keymanager.beans.KeyPairBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pan.utils.AppBizException;
import pan.utils.ca.KeyPairSizeEnum;
import pan.utils.ca.KeyPairTypeEnum;
import pan.utils.web.datatables.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import pan.utils.data.Order;
import pan.utils.web.MediaTypes;

import java.util.List;

/**
 * Created by panqingrong on 20/10/2016.
 */
@RestController
@RequestMapping("restapis/keymanager")
public class KeyManagerRestController {
    @Autowired
    private KeyPairService keyPairService;

    @RequestMapping(value = "keypair/list",
            method = RequestMethod.POST,
            consumes = MediaTypes.JSON_UTF_8, produces = MediaTypes.JSON_UTF_8)
    public DTQueryResultPagination<KeyPairInfo> listPrivateKeys(@RequestBody DTQueryPagination dtQuery) {
        DataTablesUtils<KeyPairInfo> dtUtils = new DataTablesUtils<KeyPairInfo>();
        int pageNum = dtUtils.calcPageNumber(dtQuery);
        List<Order> orders = dtUtils.getOrders(dtQuery);
        Page<KeyPairInfo> pkInfos = keyPairService.findAllKeyPairInfo(pageNum,
                dtQuery.getLength(), orders);
        DTQueryResultPagination<KeyPairInfo> queryResult =
                dtUtils.convertDataTablesQueryResult(dtQuery.getDraw(), pkInfos);
        return queryResult;
    }

    @RequestMapping(value="keypair",
            method = RequestMethod.POST,
             consumes=MediaTypes.JSON_UTF_8)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void newKeyPair(@RequestBody KeyPairBean keyPair) throws AppBizException{
        VOKeyPair voKeyPair = new VOKeyPair();
        voKeyPair.setKeyName(keyPair.getName());
        if (keyPair.getType().equals(KeyPairTypeEnum.RSA.getName())){
            voKeyPair.setKeyType(KeyPairTypeEnum.RSA);
            switch(keyPair.getSize()){
                case "1024":
                    voKeyPair.setKeySize(KeyPairSizeEnum.SIZE_1024);
                    break;
                case "2048":
                    voKeyPair.setKeySize(KeyPairSizeEnum.SIZE_2048);
                    break;
                case "4096":
                    voKeyPair.setKeySize(KeyPairSizeEnum.SIZE_4096);
                    break;
                default:
                    voKeyPair.setKeySize(KeyPairSizeEnum.SIZE_1024);
            }
            keyPairService.saveKeyPairRSA(voKeyPair);
        }

    }

    @RequestMapping(value="keypair/{sId}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKeyPair(@PathVariable("sId") Long sId){
        keyPairService.deleteKeyPair(sId);
    }
}
