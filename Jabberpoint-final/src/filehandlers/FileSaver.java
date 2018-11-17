package filehandlers;

import filehandlers.encoder.FileEncoder;
import model.Presentation;

import java.io.IOException;

/**
 * created by: jsa06
 */
public class FileSaver {
    private FileEncoder fileEncoder;

    public void setEncoder(FileEncoder fileEncoder) {
        this.fileEncoder = fileEncoder;
    }

    public Boolean saveFile(Presentation presentation, String filename) throws IOException {
        if (fileEncoder == null) {
            throw new IOException("FileParser can't be null!");
        }

        return fileEncoder.saveFile(presentation, filename);
    }
}
