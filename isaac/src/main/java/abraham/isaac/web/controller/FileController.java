package abraham.isaac.web.controller;

import abraham.core.isaac.bean.FileInfo;
import abraham.isaac.domain.File;
import abraham.isaac.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class FileController {
    @Autowired
    private FileRepository fileRepository;

    @PostMapping("filemanager/file")
    public Mono<File> create(@RequestBody File file) {
        Mono<File> result = fileRepository.save(file);
        return result;
    }

    @GetMapping("filemanager/file/{fid}")
    public Mono<FileInfo> get(@PathVariable String fid){
        Mono<FileInfo> result = fileRepository.findById(fid).map(file -> {
            abraham.core.isaac.bean.FileInfo fileInfo = new abraham.core.isaac.bean.FileInfo();
            fileInfo.setId(file.getId());
            fileInfo.setContent(file.getContent());
            return fileInfo;
        });
        return result;
    }

    @GetMapping("filemanager/sayHello")
    public String sayHello(){
        return "Hello world!";
    }

}
