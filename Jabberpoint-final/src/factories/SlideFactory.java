package factories;

import model.Slide;

/**
 * Created by ggo01
 */
public class SlideFactory {

    private static SlideFactory instance;

    public static SlideFactory getInstance() {
        if (instance == null) {
            instance = new SlideFactory();
        }
        return instance;
    }

    private SlideFactory() {

    }

    public Slide createSlide() {
        return new Slide();
    }
}
