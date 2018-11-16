package filehandlers;

import filehandlers.parser.FileParser;
import model.Presentation;

import java.io.IOException;

/**
 * Created by ggo01
 */
public class FileReader {

    private FileParser fileParser;

    public void setParser(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public Presentation readFile(String filename) throws IOException {
        if (fileParser == null) {
            throw new IOException("FileParser can't be null!");
        }

        return fileParser.parseFile(filename);
    }

}
