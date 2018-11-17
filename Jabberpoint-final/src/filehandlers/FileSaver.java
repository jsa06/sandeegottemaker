package filehandlers;

import filehandlers.encoder.FileEncoder;
import model.Presentation;

import java.io.IOException;

/**
 * created by: jsa06
 * Primary file saving class. When called an encoder has to be set to properly use it.
 */
public class FileSaver {
    private FileEncoder fileEncoder;

    public void setEncoder(FileEncoder fileEncoder) {
        this.fileEncoder = fileEncoder;
    }

    /**
     * Save the given presentation using the filename. Type is based parser.
     * @param presentation Presentation to save.
     * @param filename filename without extension
     * @return True if save is successful, false if it fails.
     * @throws IOException
     */
    public Boolean saveFile(Presentation presentation, String filename) throws IOException {
        if (fileEncoder == null) {
            throw new IOException("FileParser can't be null!");
        }

        return fileEncoder.saveFile(presentation, filename);
    }
}
