package abraham.core.web.security.repository;

import abraham.core.web.security.domain.SecurityUserPermission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SecurityUserPermissionRepository extends PagingAndSortingRepository<SecurityUserPermission, Long> {

    @Query("select sup from SecurityUserPermission sup where sup.userSid = :userSid")
    public List<SecurityUserPermission> findByUserSid(@Param("userSid") Long userSid);

}
