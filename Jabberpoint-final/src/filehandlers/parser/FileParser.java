package filehandlers.parser;

import factories.PresentationFactory;
import factories.SlideFactory;
import factories.SlideItemFactory;
import model.Presentation;

/**
 * Created by ggo01
 * Abstract FileParser class, all fileparsers should inherit from this class.
 */
public abstract class FileParser {

    protected PresentationFactory presentationFactory;

    protected SlideFactory slideFactory;

    protected SlideItemFactory slideItemFactory;

    public FileParser() {
        this.presentationFactory = PresentationFactory.getInstance();
        this.slideFactory = SlideFactory.getInstance();
        this.slideItemFactory = SlideItemFactory.getInstance();
    }

    /**
     * The method called in all cases to parse a file being fed to the parser.
     * @param filename  full file address to open
     * @return A completed presentation object.
     */
    abstract public Presentation parseFile(String filename);
}
