package moveslikejagger.repository;

import java.io.File;
import java.util.List;

public interface WriteRepository {

    String save(String content, String fileTitle, String filename);
    File getSelectedFile(String filename);
    String loadText(String filepath);
    String loadFilename(String filepath);
    List<File> loadFiles();
    List<String> loadFilenames();
    void destroy(String filepath);
    boolean checkChars(String fileTitle);
    int checkSpaceChars(String fitleTitle);
}
