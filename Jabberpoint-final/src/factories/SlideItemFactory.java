package factories;

/**
 * Created by ggo01
 */
public class SlideItemFactory {

    private static SlideItemFactory instance;

    private StyleFactory styleFactory;

    public static SlideItemFactory getInstance() {
        if (instance == null) {
            instance = new SlideItemFactory();
        }
        return instance;
    }

    private SlideItemFactory() {
        this.styleFactory = StyleFactory.getInstance();
    }
}
