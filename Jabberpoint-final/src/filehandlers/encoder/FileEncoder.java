package filehandlers.encoder;


import model.Presentation;

/**
 * created by: jsa06
 */
public abstract class FileEncoder {
    public abstract Boolean saveFile(Presentation presentation, String filename);
}
