package view.strategies;

import model.ItemStyle;
import view.JabberPointFrame;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ggo01
 */
public class SwingDrawStrategy extends DrawStrategy {

    private Graphics g;
    private Rectangle area;

    public SwingDrawStrategy(Graphics g, Rectangle area) {
        this.g = g;
        this.area = area;
    }

    @Override
    public void draw(ItemStyle itemStyle, Object toDraw) {
        if (toDraw instanceof BufferedImage) {
            this.drawImage(itemStyle, (BufferedImage) toDraw);
        } else if (toDraw instanceof String) {
            this.drawString(itemStyle, (String) toDraw);
        }
    }
    
    private void drawImage(ItemStyle itemStyle, BufferedImage bufferedImage) {
        float scale = getScale();
        int width = area.x + (int) (itemStyle.getIndentation() * scale);
        int height = area.y + (int) (itemStyle.getLeading() * scale);
        g.drawImage(bufferedImage, width, height,(int) (bufferedImage.getWidth(null) * scale),
                (int) (bufferedImage.getHeight(null) * scale), null);

        area.y += (int) (itemStyle.getLeading() * scale) + (int) (bufferedImage.getHeight(null) * scale);
    }
    
    private void drawString(ItemStyle itemStyle, String text) {
        int height = 0;
        if (text == null || text.length() == 0) {
            return;
        }

        float scale = getScale();
        java.util.List<TextLayout> layouts = this.getLayouts(text, itemStyle);
        Point pen = new Point(area.x + (int) (itemStyle.getIndentation() * scale),
                area.y + (int) (itemStyle.getLeading() * scale));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(itemStyle.getColor());
        Iterator<TextLayout> it = layouts.iterator();
        while (it.hasNext()) {
            TextLayout layout = it.next();
            pen.y += layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);
            pen.y += layout.getDescent();

            height += calculateHeight(layout, itemStyle);
        }

        area.y += height;
    }

    private int calculateHeight(TextLayout layout, ItemStyle itemStyle) {
        int height = (int) layout.getDescent() + itemStyle.getLeading();

        Rectangle2D bounds = layout.getBounds();
        if (bounds.getHeight() > 0) {
            height += bounds.getHeight();
        }

        return height;
    }

    private List<TextLayout> getLayouts(String text, ItemStyle itemStyle) {
        float scale = getScale();

        List<TextLayout> layouts = new ArrayList<TextLayout>();
        AttributedString attrStr = this.getAttributedString(text, itemStyle);
        Graphics2D g2d = (Graphics2D) g;
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
        float wrappingWidth = (JabberPointFrame.WIDTH - itemStyle.getIndentation()) * scale;
        while (measurer.getPosition() < text.length()) {
            TextLayout layout = measurer.nextLayout(wrappingWidth);
            layouts.add(layout);
        }
        return layouts;
    }

    private AttributedString getAttributedString(String text, ItemStyle itemStyle) {
        float scale = getScale();

        AttributedString attrStr = new AttributedString(text);
        attrStr.addAttribute(TextAttribute.FONT, itemStyle.getFont(scale), 0, text.length());
        return attrStr;
    }

    /**
     * Helper method to calculate the scale of the item to fit within the area.
     * @return
     */
    protected float getScale() {
        return Math.min(((float)area.width) / ((float) JabberPointFrame.WIDTH), ((float)area.height) / ((float) JabberPointFrame.HEIGHT));
    }
}
