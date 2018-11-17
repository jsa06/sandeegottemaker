package filehandlers;

import factories.FileEncoderFactory;
import factories.FileParserFactory;
import filehandlers.encoder.FileEncoder;
import filehandlers.parser.FileParser;
import model.Presentation;

import java.io.IOException;

/**
 * Created by ggo01
 */
public class FileHandler {

    private static FileHandler instance;

    private FileReader fileReader;

    private FileSaver fileSaver;

    private FileParserFactory fileParserFactory;

    private FileEncoderFactory fileEncoderFactory;

    public static FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler();
        }

        return instance;
    }

    private FileHandler() {
        this.fileReader = new FileReader();
        this.fileSaver = new FileSaver();
        this.fileParserFactory = FileParserFactory.getInstance();
        this.fileEncoderFactory = FileEncoderFactory.getInstance();
    }

    public Presentation readFile(String filename) throws IOException {
        FileParser parser = fileParserFactory.getFileParser(filename);
        fileReader.setParser(parser);

        return fileReader.readFile(filename);
    }

    public Boolean saveFile(Presentation presentation, String filetype, String filename) throws IOException {
        FileEncoder encoder = fileEncoderFactory.getFileEncoder(filetype);
        fileSaver.setEncoder(encoder);

        return fileSaver.saveFile(presentation, filename);
    }
}
