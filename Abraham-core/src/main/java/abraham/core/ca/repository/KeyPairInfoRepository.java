package abraham.core.ca.repository;

import abraham.core.ca.domain.KeyPairInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by panqingrong on 04/10/2016.
 */
public interface KeyPairInfoRepository extends PagingAndSortingRepository<KeyPairInfo, Long> {

    public KeyPairInfo findByName(String name);

}
