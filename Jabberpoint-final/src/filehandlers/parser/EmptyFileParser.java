package filehandlers.parser;

import model.Presentation;

/**
 * Created by ggo01
 */
public class EmptyFileParser extends FileParser {

    @Override
    public Presentation parseFile(String filename) {
        Presentation presentation = presentationFactory.createPresentation();
        presentation.addSlide(slideFactory.createSlide());

        return presentation;
    }
}
