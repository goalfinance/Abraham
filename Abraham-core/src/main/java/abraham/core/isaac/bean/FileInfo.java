package abraham.core.isaac.bean;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
//@NoArgsConstructor
@RequiredArgsConstructor
public class FileInfo {
    private String id;
    private byte[] content;
}
