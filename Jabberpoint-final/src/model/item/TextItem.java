package model.item;

import view.JabberPointFrame;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ggo01
 */
public class TextItem extends SlideItem {

    private String text;

    @Override
    public void setContent(String content) {
        this.text = content;
    }

    public String getContent() {
        return this.text;
    }

    @Override
    protected void drawContent(Graphics g, Rectangle area) {
        System.out.println("DRAW TEXT: " + text);

        if (text == null || text.length() == 0) {
            return;
        }

        float scale = getScale(area);
        java.util.List<TextLayout> layouts = getLayouts(g, scale);
        Point pen = new Point(area.x + (int)(this.itemStyle.getIndentation() * scale),
                area.y + (int) (this.itemStyle.getLeading() * scale));
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(this.itemStyle.getColor());
        Iterator<TextLayout> it = layouts.iterator();
        while (it.hasNext()) {
            TextLayout layout = it.next();
            pen.y += layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);
            pen.y += layout.getDescent();
        }
    }

    @Override
    protected int getHeight(Graphics g, Rectangle area) {
        return 20; //TODO berekenen
    }

    private List<TextLayout> getLayouts(Graphics g, float scale) {
        List<TextLayout> layouts = new ArrayList<TextLayout>();
        AttributedString attrStr = getAttributedString(scale);
        Graphics2D g2d = (Graphics2D) g;
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
        float wrappingWidth = (JabberPointFrame.WIDTH - this.itemStyle.getIndentation()) * scale;
        while (measurer.getPosition() < text.length()) {
            TextLayout layout = measurer.nextLayout(wrappingWidth);
            layouts.add(layout);
        }
        return layouts;
    }

    private AttributedString getAttributedString(float scale) {
        AttributedString attrStr = new AttributedString(this.text);
        attrStr.addAttribute(TextAttribute.FONT, this.itemStyle.getFont(scale), 0, text.length());
        return attrStr;
    }
}
