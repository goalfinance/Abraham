package abraham.core.web.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import abraham.core.web.security.domain.SecurityUserPermission;


public interface SecurityUserPermissionRepository extends PagingAndSortingRepository<SecurityUserPermission, Long> {

	@Query("select sup from SecurityUserPermission sup where sup.userSid = :userSid")
	public List<SecurityUserPermission> findByUserSid(@Param("userSid") Long userSid);

}
