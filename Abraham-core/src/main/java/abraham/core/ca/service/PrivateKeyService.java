package abraham.core.ca.service;

import abraham.core.ca.domain.PrivateKeyInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import pan.utils.AppBizException;
import pan.utils.data.Order;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by panqingrong on 04/10/2016.
 */
@Validated
public interface PrivateKeyService {

    public Page<PrivateKeyInfo> findAllPrivateKeyInfo(int pageNumber, int pageSize, List<Order> orders);

    public void savePrivateKeyRSA(VOPrivateKeyRSA privateKeyRSA) throws AppBizException;
}
