package abraham.core.isaac.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;


@Data
@Document
//@NoArgsConstructor
@RequiredArgsConstructor
public class File {
    @Id
    private String id;
    private byte[] content;
}
