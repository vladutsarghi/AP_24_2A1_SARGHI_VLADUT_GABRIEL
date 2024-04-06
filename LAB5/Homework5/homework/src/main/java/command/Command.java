package command;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public interface Command {
    void checkFileLocation(String fileLocation) throws IOException, TemplateException;

    void implementCommand(File file) throws IOException, TemplateException;

}
