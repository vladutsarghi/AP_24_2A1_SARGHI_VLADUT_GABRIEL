package org.example;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import freemarker.template.TemplateException;
import myException.IllegalCommand;
import myException.NonExistentCommand;
import program.Program;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        Program program = new Program();


        try{
        program.run();}
        catch(IllegalCommand | NonExistentCommand e) {
            System.err.println("Error: " + e.getMessage());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}

// export C:/Users/sargh/Desktop/MasterDir
