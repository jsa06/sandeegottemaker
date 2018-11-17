package factories;

import model.Presentation;

/**
 * Created by ggo01
 */
public class PresentationFactory {

    private static PresentationFactory instance;

    public static PresentationFactory getInstance() {
        if (instance == null) {
            instance = new PresentationFactory();
        }
        return instance;
    }

    private PresentationFactory() {

    }

    public Presentation createPresentation() {
        return new Presentation();
    }
}
