package view.strategies;

import model.ItemStyle;

/**
 * Created by ggo01
 * Abstract Class for setting up the draw strategy.
 */
public abstract class DrawStrategy {

    abstract public void draw(ItemStyle itemStyle, Object toDraw);
}
