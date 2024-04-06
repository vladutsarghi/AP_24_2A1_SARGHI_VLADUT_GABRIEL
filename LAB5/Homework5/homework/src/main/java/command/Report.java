package command;

import freemarker.template.*;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Report implements Command {
    public Report(String fl) throws TemplateException, IOException {
        checkFileLocation(fl);
    }

    @Override
    public void checkFileLocation(String fileLocation) throws TemplateException, IOException {
        File file = new File(fileLocation);
        if (file.exists()) {
            implementCommand(file);
        }
    }

    @Override
    public void implementCommand(File file2) throws IOException, TemplateException {

        File rootFolder = file2;
        if (!rootFolder.isDirectory()) {
            System.err.println("The provided path is not a valid directory.");
            System.exit(1);
        }

        // Prepare the data model
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("folders", listFolderContents(rootFolder));

        // Configure FreeMarker
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDirectoryForTemplateLoading(new File("C:\\Users\\sargh\\Desktop\\AP_24_2A1_SARGHI_VLADUT_GABRIEL\\LAB5\\Homework5\\homework"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // Generate HTML report
        Template template = cfg.getTemplate("template.ftl");
        Writer fileWriter = new FileWriter(new File("folder_structure_report.html"));
        template.process(dataModel, fileWriter);
        fileWriter.close();
    }

    private static List<Map<String, Object>> listFolderContents(File folder) {
        List<Map<String, Object>> contents = new ArrayList<>();
        File[] filesList = folder.listFiles();

        if (filesList != null) {
            for (File file : filesList) {
                Map<String, Object> fileMap = new HashMap<>();
                fileMap.put("name", file.getName());
                if (file.isDirectory()) {
                    fileMap.put("type", "folder");
                    fileMap.put("children", listFolderContents(file));
                } else {
                    fileMap.put("type", "file");
                }
                contents.add(fileMap);
            }
        }

        return contents;
    }
}


 class FolderContent {
    private List<String> files;
    private List<FolderContent> subFolders;
    private String folderName;

    public FolderContent() {
        this.files = new ArrayList<>();
        this.subFolders = new ArrayList<>();
    }

    public FolderContent(String folderName) {
        this.folderName = folderName;
        this.files = new ArrayList<>();
        this.subFolders = new ArrayList<>();
    }

    public void addFile(String fileName) {
        files.add(fileName);
    }

    public void addSubFolder(FolderContent subFolder) {
        subFolders.add(subFolder);
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public List<FolderContent> getSubFolders() {
        return subFolders;
    }

    public void setSubFolders(List<FolderContent> subFolders) {
        this.subFolders = subFolders;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}


