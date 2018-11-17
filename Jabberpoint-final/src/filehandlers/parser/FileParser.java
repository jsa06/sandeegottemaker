package filehandlers.parser;

import factories.PresentationFactory;
import factories.SlideFactory;
import factories.SlideItemFactory;
import model.Presentation;

/**
 * Created by ggo01
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

    abstract public Presentation parseFile(String filename);
}
