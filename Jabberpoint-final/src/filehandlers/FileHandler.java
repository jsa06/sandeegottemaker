package filehandlers;

import factories.FileParserFactory;
import filehandlers.parser.FileParser;
import model.Presentation;

import java.io.IOException;

/**
 * Created by ggo01
 */
public class FileHandler {

    private FileReader fileReader;

    private FileParserFactory fileParserFactory;

    public FileHandler() {
        this.fileReader = new FileReader();
        this.fileParserFactory = FileParserFactory.getInstance();
    }

    public Presentation readFile(String filename) throws IOException {
        FileParser parser = fileParserFactory.getFileParser(filename);
        fileReader.setParser(parser);

        return fileReader.readFile(filename);
    }
}
