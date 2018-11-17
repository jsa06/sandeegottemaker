package filehandlers;

import filehandlers.parser.FileParser;
import model.Presentation;

import java.io.IOException;

/**
 * Created by ggo01
 * Primary file reader class to handle opening files. Parser has to be set when called to correctly parse the file.
 */
public class FileReader {

    private FileParser fileParser;

    public void setParser(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    /**
     * Read the actual file using the set parser.
     * @param filename full path of filename
     * @return Presentation object resulting from the file.
     * @throws IOException
     */
    public Presentation readFile(String filename) throws IOException {
        if (fileParser == null) {
            throw new IOException("FileParser can't be null!");
        }

        return fileParser.parseFile(filename);
    }

}
