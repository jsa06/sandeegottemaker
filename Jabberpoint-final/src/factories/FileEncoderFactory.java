package factories;

import filehandlers.encoder.FileEncoder;
import filehandlers.encoder.XMLFileEncoder;

/**
 * created by jsa06
 * Create an Encoder for saving the presentation to a file.
 */
public class FileEncoderFactory {
    private static FileEncoderFactory instance;

    private XMLFileEncoder xmlFileEncoder;

    public static FileEncoderFactory getInstance() {
        if (instance == null) {
            instance = new FileEncoderFactory();
        }

        return instance;
    }

    private FileEncoderFactory() {
        this.xmlFileEncoder = new XMLFileEncoder();
    }

    /**
     * Returns the correct encoder based on selected Filetype.
     * NOTE: XML is the default filetype, and currently the only supported filetype.
     * @param filetype
     * @return FileEncoder based on chosed selected FileType.
     */
    public FileEncoder getFileEncoder(String filetype) {

        if(filetype == null || "".equals(filetype)) {
            return xmlFileEncoder;
        }

        return xmlFileEncoder;
    }
}
