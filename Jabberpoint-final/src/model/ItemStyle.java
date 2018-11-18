package model;

import java.awt.*;

/**
 * Created by ggo01
 * Item Style model that defines all possible settings for a styling.
 */
public class ItemStyle {

    private int indentation;

    private Color color;

    private int points;

    private int leading;

    public ItemStyle(int indentation, Color color, int points, int leading) {
        this.indentation = indentation;
        this.color = color;
        this.points = points;
        this.leading = leading;
    }

    public int getIndentation() {
        return indentation;
    }

    public Color getColor() {
        return color;
    }

    public int getPoints() {
        return points;
    }

    public int getLeading() {
        return leading;
    }

    /**
     * NOTE: Currently the default is font is Helvetica.
     * @param scale
     * @return
     */
    public Font getFont(float scale) {
        Font font = new Font("Helvetica", Font.BOLD, points);
        return font.deriveFont(points * scale);
    }
}
