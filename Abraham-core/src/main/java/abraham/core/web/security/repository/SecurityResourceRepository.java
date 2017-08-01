package abraham.core.web.security.repository;

import abraham.core.web.security.domain.SecurityResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecurityResourceRepository extends PagingAndSortingRepository<SecurityResource, Long> {

    @Query("select sr from SecurityResource sr where sr.groupSid = :groupSid order by sr.sortIdx asc")
    public List<SecurityResource> findByGroupSidOrderBySortIdx(@Param("groupSid") Long groupId);

}
