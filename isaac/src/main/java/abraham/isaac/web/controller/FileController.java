package abraham.isaac.web.controller;

import abraham.core.isaac.domain.File;
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
    public Mono<File> get(@PathVariable String fid){
        Mono<File> result = fileRepository.findById(fid);
        return result;
    }

    @GetMapping("filemanager/sayHello")
    public String sayHello(){
        return "Hello world!";
    }

}
