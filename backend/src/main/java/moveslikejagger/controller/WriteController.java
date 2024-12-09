package moveslikejagger.controller;

import moveslikejagger.controller.requests.WriteSaveRequest;
import moveslikejagger.repository.WriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WriteController {

    @Autowired
    private WriteRepository writeRepository;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody WriteSaveRequest writeSaveRequest) {
        String filepath = writeRepository.save(writeSaveRequest.getContent(), writeSaveRequest.getTitle(), writeSaveRequest.getFilename());
        return new ResponseEntity(filepath, HttpStatus.OK);
    }

    @GetMapping("/getSelectedFile")
    public ResponseEntity getSelectedFile(@RequestParam String filename) {
        File file = writeRepository.getSelectedFile(filename);
        return new ResponseEntity(file, HttpStatus.OK);
    }

    @GetMapping("/loadText")
    public ResponseEntity loadText(@RequestParam String filepath) {
        String text = writeRepository.loadText(filepath);
        return new ResponseEntity(text, HttpStatus.OK);
    }

    @GetMapping("/loadFilename")
    public ResponseEntity loadFilename(@RequestParam String filepath) {
        String title = writeRepository.loadFilename(filepath);
        return new ResponseEntity(title, HttpStatus.OK);
    }

    @GetMapping("/loadFiles")
    public ResponseEntity loadFiles() {
        List<File> files = writeRepository.loadFiles();
        return new ResponseEntity(files, HttpStatus.OK);
    }

    @GetMapping("/loadFilenames")
    public ResponseEntity loadFilenames() {
        List<String> filenames = writeRepository.loadFilenames();
        return new ResponseEntity(filenames, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam String filepath) {
        writeRepository.destroy(filepath);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/checkChars")
    public ResponseEntity checkChars(@RequestParam String fileTitle) {
        boolean illegal = writeRepository.checkChars(fileTitle);
        return new ResponseEntity(illegal, HttpStatus.OK);
    }

    @GetMapping("/checkSpaceChars")
    public ResponseEntity checkSpaceChars(@RequestParam String fileTitle) {
        int spaceCount = writeRepository.checkSpaceChars(fileTitle);
        return new ResponseEntity(spaceCount, HttpStatus.OK);
    }
}
