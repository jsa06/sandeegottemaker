package filehandlers.parser;

import factories.PresentationFactory;
import factories.SlideFactory;
import factories.SlideItemFactory;
import model.Presentation;

/**
 * Created by ggo01
 */
public abstract class FileParser {

    private PresentationFactory presentationFactory;

    private SlideFactory slideFactory;

    private SlideItemFactory slideItemFactory;

    public FileParser() {
        this.presentationFactory = PresentationFactory.getInstance();
        this.slideFactory = SlideFactory.getInstance();
        this.slideItemFactory = SlideItemFactory.getInstance();
    }

    abstract public Presentation parseFile(String filename);
}
