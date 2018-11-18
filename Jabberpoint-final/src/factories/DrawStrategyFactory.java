package factories;

import view.strategies.DrawStrategy;
import view.strategies.SwingDrawStrategy;

import java.awt.*;

/**
 * Created by ggo01
 * Generate the correct DrawStrategy based on current GUI requirements.
 * NOTE: Currently only Swing is supported.
 */
public class DrawStrategyFactory {

    private static DrawStrategyFactory instance;

    public static DrawStrategyFactory getInstance() {
        if (instance == null) {
            instance = new DrawStrategyFactory();
        }
        return instance;
    }

    private DrawStrategyFactory() {}

    /**
     * Specific creator for SwingDrawStrategy. Returns abstract DrawStrategy to discourage misuse of Factory
     * @param g The current Graphics object
     * @param area The area to draw in
     * @return DrawStrategy that is capable of drawing on the screen
     */
    public DrawStrategy createStrategy(Graphics g, Rectangle area) {
        return new SwingDrawStrategy(g, area);
    }
}
