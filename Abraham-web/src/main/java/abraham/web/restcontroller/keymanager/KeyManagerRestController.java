package abraham.web.restcontroller.keymanager;

import abraham.core.ca.domain.KeyPairInfo;
import abraham.core.ca.service.KeyExportRequest;
import abraham.web.restcontroller.keymanager.beans.KeyExportReqBean;
import abraham.web.restcontroller.keymanager.beans.KeyPairBean;
import abraham.web.service.keymanager.KeyManagerService;
import abraham.web.service.keymanager.models.ExportKeyRequest;
import abraham.web.service.keymanager.models.GenerateKeyPairRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import pan.utils.AppBizException;
import pan.utils.ca.KeyPairSizeEnum;
import pan.utils.ca.KeyPairTypeEnum;
import pan.utils.data.Order;
import pan.utils.web.MediaTypes;
import pan.utils.web.datatables.DTQueryPagination;
import pan.utils.web.datatables.DTQueryResultPagination;
import pan.utils.web.datatables.DataTablesUtils;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by panqingrong on 20/10/2016.
 */
@RestController
@RequestMapping("restapis/keymanager")
public class KeyManagerRestController {
    @Autowired
    private KeyManagerService keyManagerService;

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping(value = "keypair/list",
            method = RequestMethod.POST,
            consumes = MediaTypes.JSON_UTF_8, produces = MediaTypes.JSON_UTF_8)
    public DTQueryResultPagination<KeyPairInfo> listPrivateKeys(@RequestBody DTQueryPagination dtQuery) {
        DataTablesUtils<KeyPairInfo> dtUtils = new DataTablesUtils<KeyPairInfo>();
        int pageNum = dtUtils.calcPageNumber(dtQuery);
        List<Order> orders = dtUtils.getOrders(dtQuery);
        Page<KeyPairInfo> pkInfos = keyManagerService.findAllKeyPairs(pageNum,
                dtQuery.getLength(), orders);
        DTQueryResultPagination<KeyPairInfo> queryResult =
                dtUtils.convertDataTablesQueryResult(dtQuery.getDraw(), pkInfos);
        return queryResult;
    }

    @RequestMapping(value = "keypair",
            method = RequestMethod.POST,
            consumes = MediaTypes.JSON_UTF_8)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void newKeyPair(@RequestBody KeyPairBean keyPair) throws AppBizException {
        GenerateKeyPairRequest request = new GenerateKeyPairRequest();
        request.setKeyName(keyPair.getName());
        request.setKeyType(KeyPairTypeEnum.valueOf(keyPair.getType()));
        request.setKeySize(KeyPairSizeEnum.valueOf("SIZE_" + keyPair.getSize()));

        keyManagerService.generateKeyPair(request);

    }

    @RequestMapping(value = "keypair/{sId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKeyPair(@PathVariable("sId") Long sId) throws AppBizException{
        keyManagerService.deleteKeyPair(sId);
    }
}
