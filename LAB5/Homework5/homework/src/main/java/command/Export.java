package command;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Export implements Command{

    public Export(String filePath){
        checkFileLocation(filePath);
    }
    @Override
    public void checkFileLocation(String fileLocation) {
        File file = new File(fileLocation);
        if (file.exists()) {
            implementCommand(file);
        }
    }

    @Override
    public void implementCommand(File file) {
        System.out.println("here");
        DirectoryNode directoryStructure = visitDirectory(file);

        ObjectMapper mapper = new ObjectMapper();
        try {
            File fileToSaveJSON = new File("structureOfDirectory.json");
            FileWriter fileWriter = new FileWriter(fileToSaveJSON);

            mapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, directoryStructure);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DirectoryNode visitDirectory(File directory) {
        DirectoryNode node = new DirectoryNode(directory.getName(), new ArrayList<>(), new ArrayList<>());
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    node.getDirectories().add(visitDirectory(file));
                } else {
                    node.getFiles().add(file.getName());
                }
            }
        }
        return node;
    }

    public static class DirectoryNode {
        private String name;
        private List<DirectoryNode> directories;
        private List<String> files;

        public DirectoryNode(String name, List<DirectoryNode> directories, List<String> files) {
            this.name = name;
            this.directories = directories;
            this.files = files;
        }

        // Getters and setters for name, directories, and files
        public String getName() {
            return name;
        }

        public List<DirectoryNode> getDirectories() {
            return directories;
        }

        public List<String> getFiles() {
            return files;
        }
    }
}
