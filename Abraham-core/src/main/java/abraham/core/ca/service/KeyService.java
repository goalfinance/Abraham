package abraham.core.ca.service;

import abraham.core.ca.domain.KeyExtInfo;
import abraham.core.ca.domain.KeyPairInfo;
import org.springframework.data.domain.Page;
import pan.utils.AppBizException;
import pan.utils.data.Order;

import java.io.OutputStream;
import java.util.List;

public interface KeyService {

    public KeyExtInfo findKeyExtInfoBySid(long sid);

    public KeyPairInfo findKeyPairInfoBySid(long sid);

    public Page<KeyPairInfo> findAllKeyPairInfo(int pageNumber, int pageSize, List<Order> orders);

    public void deleteKeyPair(long sid);

    public void generateKeyPair(KeyGenerateRequest keyPairInfo) throws AppBizException;

    public void exportKey(KeyExportRequest req, OutputStream outputStream) throws AppBizException;
}
