package abraham.web.service.keymanager;

import abraham.core.ca.domain.DSAKeyExtInfo;
import abraham.core.ca.domain.KeyPairInfo;
import abraham.core.ca.domain.RSAKeyExtInfo;
import abraham.web.service.keymanager.models.ExportKeyRequest;
import abraham.web.service.keymanager.models.GenerateKeyPairRequest;
import org.springframework.data.domain.Page;
import pan.utils.AppBizException;
import pan.utils.data.Order;

import java.io.OutputStream;
import java.util.List;

public interface KeyManagerService {

    public void generateKeyPair(GenerateKeyPairRequest request) throws AppBizException;

    public Page<KeyPairInfo> findAllKeyPairs(int pageNumber, int pageSize, List<Order> orders);

    public void deleteKeyPair(long sid) throws AppBizException;

    public RSAKeyExtInfo findRSAKeyExtInfoBySid(long sid) throws AppBizException;

    public DSAKeyExtInfo findDSAKeyExtInfoBySid(long sid) throws AppBizException;

    public KeyPairInfo findKeyPairInfoBySid(long sid) throws AppBizException;

    public KeyPairInfo updateKeyPairInfo(KeyPairInfo keyPairInfo) throws AppBizException;

    public void exportKey(ExportKeyRequest req, OutputStream outputStream) throws AppBizException;
}
