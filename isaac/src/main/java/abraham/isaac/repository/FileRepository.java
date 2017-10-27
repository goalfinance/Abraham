package abraham.isaac.repository;

import abraham.isaac.domain.File;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FileRepository extends ReactiveCrudRepository<File, String> {
}
