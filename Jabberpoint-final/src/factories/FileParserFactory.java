package factories;

import filehandlers.parser.DemoFileParser;
import filehandlers.FileReader;
import filehandlers.parser.FileParser;
import filehandlers.parser.XMLFileParser;

/**
 * Created by ggo01
 */
public class FileParserFactory {

    private static FileParserFactory instance;

    private XMLFileParser xmlFileParser;

    private DemoFileParser demoFileParser;

    public static FileParserFactory getInstance() {
        if (instance == null) {
            instance = new FileParserFactory();
        }

        return instance;
    }

    private FileParserFactory() {
        this.xmlFileParser = new XMLFileParser();
        this.demoFileParser = new DemoFileParser();
    }

    public FileParser getFileParser(String filename) {
        if ("".equals(filename)) { // Automatically checks for null
            return demoFileParser;
        }

        return xmlFileParser;
    }
}
