package filehandlers;

import factories.FileParserFactory;
import filehandlers.parser.FileParser;
import model.Presentation;

import java.io.IOException;

/**
 * Created by ggo01
 */
public class FileHandler {

    private static FileHandler instance;

    private FileReader fileReader;

    private FileParserFactory fileParserFactory;

    public static FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler();
        }

        return instance;
    }

    private FileHandler() {
        this.fileReader = new FileReader();
        this.fileParserFactory = FileParserFactory.getInstance();
    }

    public Presentation readFile(String filename) throws IOException {
        FileParser parser = fileParserFactory.getFileParser(filename);
        fileReader.setParser(parser);

        return fileReader.readFile(filename);
    }
}
