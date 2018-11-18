package filehandlers;

import factories.FileEncoderFactory;
import factories.FileParserFactory;
import filehandlers.encoder.FileEncoder;
import filehandlers.parser.FileParser;
import model.Presentation;

import java.io.IOException;

/**
 * Created by ggo01
 * Filehandler handles all the saving and loading of files.
 */
public class FileHandler {

    private static FileHandler instance;

    private FileReader fileReader;

    private FileSaver fileSaver;

    private FileParserFactory fileParserFactory;

    private FileEncoderFactory fileEncoderFactory;

    /**
     * File handler only ever needs to be a single instance.
     * @return
     */
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

    /**
     * Read the filename and set the correct parser for reading this file.
     * @param filename full file path of the file.
     * @return Presentation object
     * @throws IOException
     */
    public Presentation readFile(String filename) throws IOException {
        FileParser parser = fileParserFactory.getFileParser(filename);
        fileReader.setParser(parser);

        return fileReader.readFile(filename);
    }

    /**
     * Save the presentation to a file to using the given filetype.
     * @param presentation presentation to save.
     * @param filetype type to save the file in, also defines extension.
     * @param filename name of the file without extension.
     * @return True if the save is successful, false is the save failed.
     * @throws IOException
     */
    public boolean saveFile(Presentation presentation, String filetype, String filename) throws IOException {
        FileEncoder encoder = fileEncoderFactory.getFileEncoder(filetype);
        fileSaver.setEncoder(encoder);

        return fileSaver.saveFile(presentation, filename);
    }
}
