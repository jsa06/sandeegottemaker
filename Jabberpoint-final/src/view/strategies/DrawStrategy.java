package view.strategies;

import model.ItemStyle;

/**
 * Created by ggo01
 */
public abstract class DrawStrategy {

    abstract public void draw(ItemStyle itemStyle, Object toDraw);
}
