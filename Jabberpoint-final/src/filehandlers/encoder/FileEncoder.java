package filehandlers.encoder;


import model.Presentation;

/**
 * created by: jsa06
 * Abstract File encoder class.
 */
public abstract class FileEncoder {
    /**
     * Primary function that is called when a file is saved.
     * @param presentation The presentation to Save.
     * @param filename The filename without extension
     * @return True if the save was successful, false if it failed.
     */
    public abstract boolean saveFile(Presentation presentation, String filename);
}
