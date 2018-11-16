package factories;

/**
 * Created by ggo01
 */
public class StyleFactory {

    private static StyleFactory instance;

    public static StyleFactory getInstance() {
        if (instance == null) {
            instance = new StyleFactory();
        }
        return instance;
    }

    private StyleFactory() {

    }
}
