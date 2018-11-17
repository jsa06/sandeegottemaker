package factories;

import filehandlers.parser.DemoFileParser;
import filehandlers.FileReader;
import filehandlers.parser.EmptyFileParser;
import filehandlers.parser.FileParser;
import filehandlers.parser.XMLFileParser;

/**
 * Created by ggo01
 */
public class FileParserFactory {

    private static FileParserFactory instance;

    private XMLFileParser xmlFileParser;

    private DemoFileParser demoFileParser;

    private EmptyFileParser emptyFileParser;

    public static FileParserFactory getInstance() {
        if (instance == null) {
            instance = new FileParserFactory();
        }

        return instance;
    }

    private FileParserFactory() {
        this.xmlFileParser = new XMLFileParser();
        this.demoFileParser = new DemoFileParser();
        this.emptyFileParser = new EmptyFileParser();
    }

    public FileParser getFileParser(String filename) {
        if (filename == null) {
            return emptyFileParser;
        }
        if ("".equals(filename)) {
            return demoFileParser;
        }

        return xmlFileParser;
    }
}
