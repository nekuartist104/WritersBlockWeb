package moveslikejagger.repository;

import com.youbenzi.mdtool.tool.MDTool;
import io.github.furstenheim.CopyDown;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Repository
public class WriteRepositoryImpl implements WriteRepository {

    private final String usersDir = System.getProperty("user.home");
    private List<Integer> ids = new ArrayList<>();

    @Override
    public String save(String content, String fileTitle, String filepath) {
        int id = 0;
        if (ids.size() > 0) {
            id = ids.get(ids.size() - 1 ) + 1;
        }

        if (filepath.equals("")) {
            filepath = usersDir + "\\Web\\" + id + "_" + fileTitle + ".md";
        }

        File file = new File(filepath);
        String oldName = file.getName();
        int underscoreIndex = oldName.indexOf("_");
        String newName = filepath.replace(oldName.substring(underscoreIndex+1), fileTitle + ".md");
        File fileRenamed = new File(newName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                ids.add(id);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        file.renameTo(fileRenamed);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileRenamed);
            fileWriter.write(htmlToMarkdown(content));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return newName;
    }

    @Override
    public File getSelectedFile(String filepath) {
        File file = new File(filepath);
        return file;
    }

    @Override
    public String loadText(String filepath) {
        String text = null;
        try {
            if (!filepath.contains(".md")) {
                text = markdownToHtml(getTextFromFile(new File(filepath + ".md")));
            }
            else {
                text = markdownToHtml(getTextFromFile(new File(filepath)));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

    @Override
    public String loadFilename(String filepath) {
        File file = new File(filepath);
        int underscoreIndex = file.getName().indexOf("_");
        String title = file.getName().substring(underscoreIndex+1, file.getName().length()-3);
        return title;
    }

    @Override
    public List<File> loadFiles() {
        List<File> files = null;
        File directory = new File(usersDir + "\\Web");
        int id = 0;
        if (ids.size() > 0) {
            id = ids.get(ids.size() - 1) + 1;
        }
        File defaultFile = new File(usersDir + "\\Web\\" + id + "_Untitled.md");
        if (!directory.exists()) {
            directory.mkdir();
            try {
                defaultFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            File[] directoryListing = directory.listFiles();
            files = List.of(directoryListing);
            if (files.size() == 0) {
                try {
                    defaultFile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return files;
    }

    @Override
    public List<String> loadFilenames() {
        List<String> names = new ArrayList<>();
        File directory = new File(usersDir + "\\Web");
        if (!directory.exists()) {
            directory.mkdir();
        }
        else {
            File[] directoryListing = directory.listFiles();
            if (directoryListing != null && directoryListing.length != 0) {
                ids.clear();
                for (File f : directoryListing) {
                    int underscoreIndex = f.getName().indexOf("_");
                    String title = f.getName().substring(underscoreIndex+1, f.getName().length()-3);
                    names.add(title);
                    ids.add(Integer.valueOf(f.getName().substring(0, underscoreIndex)));
                }
                Collections.sort(ids, new IdComparator());
            }
        }
        return names;
    }

    @Override
    public void destroy(String filepath) {
        File directory = new File(usersDir + "\\Web");

        if (filepath.equals("")) {
            int id = 0;
            if (ids.size() > 0) {
                id = ids.get(ids.size()-1);
            }

            if (directory.listFiles() != null && directory.listFiles().length != 0) {
                File[] files = directory.listFiles();
                for (File f : files) {
                    if (f.getName().startsWith(id + "_")) {
                        f.delete();
                        ids.remove(ids.indexOf(id));
                    }
                }
            }
        }

        else {
            if (directory.listFiles() != null && directory.listFiles().length != 0) {
                for (File f : directory.listFiles()) {
                    File file = new File(filepath);
                    int underscoreIndex = f.getName().indexOf("_");
                    int id = Integer.parseInt(file.getName().substring(0, underscoreIndex));
                    if (f.getName().startsWith(id + "_")) {
                        f.delete();
                        ids.remove(ids.indexOf(id));
                    }
                }
            }
        }
    }

    @Override
    public boolean checkChars(String fileTitle) {
        List<String> illegalChars = new ArrayList<>();
        String[] illegalCharacters = {"\\", "<", ">", "*", "?", "/", "|", ":", "\""};
        illegalChars.addAll(List.of(illegalCharacters));
        boolean illegal = false;
        for (String s : illegalChars) {
            if (fileTitle.contains(s)) {
                illegal = true;
                break;
            }
        }
        return illegal;
    }

    @Override
    public int checkSpaceChars(String fileTitle) {
        int spaceCount = 0;
        for (int i = 0; i < fileTitle.length(); i++) {
            String c = String.valueOf(fileTitle.charAt(i));
            if (c.equalsIgnoreCase(" ")) {
                spaceCount = spaceCount+1;
            }
        }
        return spaceCount;
    }

    public String getTextFromFile(File file) throws FileNotFoundException {
        Scanner myReader = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (myReader.hasNextLine()) {
            lines.add(myReader.nextLine());
        }
        myReader.close();

        if (lines.isEmpty()) {
            return "";
        }
        return String.join("\n", lines);
    }

    public String markdownToHtml(String text) {
        String html = MDTool.markdown2Html(text);
        html = html.replaceAll("<strong>", "<b>");
        html = html.replaceAll("</strong>", "</b>");
        html = html.replaceAll("<em>", "<i>");
        html = html.replaceAll("</em>", "</i>");
        html = html.replaceAll("<p>", "<p style=\"margin-top: 0\">");

        return html;
    }

    public String htmlToMarkdown(String text) {
        CopyDown copyDown = new CopyDown();
        return copyDown.convert(text);
    }

    class IdComparator implements java.util.Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }
}
