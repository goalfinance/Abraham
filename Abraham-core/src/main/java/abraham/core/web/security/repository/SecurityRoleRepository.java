/**
 *
 */
package abraham.core.web.security.repository;

import abraham.core.web.security.domain.SecurityRole;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author panqingrong
 */
public interface SecurityRoleRepository extends PagingAndSortingRepository<SecurityRole, Long> {

}
