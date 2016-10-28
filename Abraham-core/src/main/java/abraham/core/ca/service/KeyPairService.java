package abraham.core.ca.service;

import abraham.core.ca.domain.KeyPairInfo;
import abraham.core.ca.domain.RSAKeyInfo;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import pan.utils.AppBizException;
import pan.utils.data.Order;

import java.util.List;

/**
 * Created by panqingrong on 04/10/2016.
 */
@Validated
public interface KeyPairService {

    public Page<KeyPairInfo> findAllKeyPairInfo(int pageNumber, int pageSize, List<Order> orders);

    public RSAKeyInfo findKeyPairRSAInfoBySid(long sid);

    public void deleteKeyPair(long sid);

    public void saveKeyPairRSA(VOKeyPair keyPair) throws AppBizException;
}
