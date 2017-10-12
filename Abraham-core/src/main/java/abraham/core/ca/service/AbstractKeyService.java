package abraham.core.ca.service;

import abraham.core.ca.domain.KeyPairInfo;
import abraham.core.ca.repository.KeyPairInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pan.utils.ca.KeyPairUtils;
import pan.utils.data.Order;

import java.util.List;
import java.util.Optional;

public abstract class AbstractKeyService implements KeyService {
    private KeyPairUtils keyPairUtils = new KeyPairUtils();

    @Autowired
    private KeyPairInfoRepository keyPairInfoRepository;

    protected KeyPairUtils getKeyPairUtils() {
        return keyPairUtils;
    }

    protected void setKeyPairUtils(KeyPairUtils keyPairUtils) {
        this.keyPairUtils = keyPairUtils;
    }

    protected KeyPairInfoRepository getKeyPairInfoRepository() {
        return keyPairInfoRepository;
    }

    protected void setKeyPairInfoRepository(KeyPairInfoRepository keyPairInfoRepository) {
        this.keyPairInfoRepository = keyPairInfoRepository;
    }

    @Override
    public Page<KeyPairInfo> findAllKeyPairInfo(int pageNumber, int pageSize, List<Order> orders) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Order.newSort(orders));
        return keyPairInfoRepository.findAll(pageRequest);
    }

    @Override
    public KeyPairInfo findKeyPairInfoBySid(long sid) {
        Optional<KeyPairInfo> result = keyPairInfoRepository.findById(Long.valueOf(sid));
        return result.orElse(null);
    }
}
