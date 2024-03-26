package org.example;

import java.io.File;

public class Repository{
    private final String masterDirectory;

    public Repository(String masterDirectory) {
        this.masterDirectory = masterDirectory;
    }

    public void displayRepoContent(){
        File masterDir = new File(masterDirectory);
        if(!masterDir.exists() || !masterDir.isDirectory()){
            System.out.println("Master directory doesn't exist.");
            return;
        }

        File[] personDirectories = masterDir.listFiles();
        if(personDirectories == null || personDirectories.length == 0){
            System.out.println("No person directories found.");
            return;
        }

        System.out.println("Repo content:");
        for(File personDirectory : personDirectories){
            if(personDirectory.isDirectory()){
                System.out.println("Person: " + personDirectory.getName());
                File[] documents = personDirectory.listFiles();
                if(documents!=null){
                    for(File document : documents){
                        if(document.isFile()){
                            System.out.println("\tDocument: " + document.getName());
                        }
                    }
                }
            }
        }
    }
}