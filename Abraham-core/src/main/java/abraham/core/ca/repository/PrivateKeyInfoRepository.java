package abraham.core.ca.repository;

import abraham.core.ca.domain.PrivateKeyInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by panqingrong on 04/10/2016.
 */
public interface PrivateKeyInfoRepository extends PagingAndSortingRepository<PrivateKeyInfo, Long> {

    public PrivateKeyInfo findByName(String name);

}
