package com.company.app1.ctrl;

import com.company.app1.biz.DataroomService;
import com.company.app1.entity.Dataroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dataroom")
public class DataroomController {

    @Autowired
    private DataroomService dataroomService;

    private final String UPLOAD_DIR = "uploads/";

    @GetMapping("/list")
    public List<Dataroom> getAllDatarooms() {
        return dataroomService.getAllDatarooms();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Dataroom> getDataroomById(@PathVariable int id) {
        Optional<Dataroom> dataroom = dataroomService.getDataroomById(id);
        return dataroom.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/insert")
    public Dataroom createDataroom(@RequestParam String title,
                                   @RequestParam String content,
                                   @RequestParam String author,
                                   @RequestParam MultipartFile datafile) throws IOException {
        String datafilePath = saveFile(datafile);

        Dataroom dataroom = new Dataroom();
        dataroom.setTitle(title);
        dataroom.setContent(content);
        dataroom.setAuthor(author);
        dataroom.setDatafile(datafilePath);
        return dataroomService.saveDataroom(dataroom);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Dataroom> updateDataroom(@PathVariable int id,
                                                   @RequestParam String title,
                                                   @RequestParam String content,
                                                   @RequestParam String author,
                                                   @RequestParam MultipartFile datafile) throws IOException {
        deleteFile(dataroomService.getDataroomById(id).orElseThrow().getDatafile());

        String datafilePath = saveFile(datafile);

        Dataroom updatedDataroom = new Dataroom();
        updatedDataroom.setTitle(title);
        updatedDataroom.setContent(content);
        updatedDataroom.setAuthor(author);
        updatedDataroom.setDatafile(datafilePath);

        return ResponseEntity.ok(dataroomService.updateDataroom(id, updatedDataroom));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDataroom(@PathVariable int id) {
        deleteFile(dataroomService.getDataroomById(id).orElseThrow().getDatafile());
        dataroomService.deleteDataroom(id);
        return ResponseEntity.noContent().build();
    }

    private String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }
        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return filePath;
    }

    private void deleteFile(String filePath) {
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}