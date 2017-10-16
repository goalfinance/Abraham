package abraham.core.web.security.repository;

import abraham.core.web.security.domain.SecurityUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface SecurityUserRepository extends PagingAndSortingRepository<SecurityUser, Long> {

    @Query("select su from SecurityUser su where su.userId = :userId")
    public Optional<SecurityUser> findByUserId(@Param("userId") String userId);

    @Query("select su from SecurityUser su where su.userId like ?1 ")
    public Page<SecurityUser> findByUserIdLike(String userId, Pageable pageable);

}
