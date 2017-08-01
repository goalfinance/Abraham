/**
 *
 */
package abraham.core.web.security.repository;

import abraham.core.web.security.domain.SecurityResourceGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author panqingrong
 */
public interface SecurityResourceGroupRepository extends PagingAndSortingRepository<SecurityResourceGroup, Long> {

    @Query("Select srp from SecurityResourceGroup srp order by srp.sortIdx asc")
    public List<SecurityResourceGroup> findAllOrderBySortIdx();

    public List<SecurityResourceGroup> findAllBySidInOrderBySortIdxAsc(Collection<Long> sids);

}
