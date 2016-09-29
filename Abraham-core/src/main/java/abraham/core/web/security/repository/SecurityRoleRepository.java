/**
 * 
 */
package abraham.core.web.security.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import abraham.core.web.security.domain.SecurityRole;

/**
 * @author panqingrong
 *
 */
public interface SecurityRoleRepository extends PagingAndSortingRepository<SecurityRole, Long> {

}
