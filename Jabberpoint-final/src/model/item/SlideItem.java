package model.item;

import model.ItemStyle;
import view.JabberDrawable;
import view.JabberPointComponent;
import view.JabberPointFrame;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Vector;

/**
 * Created by ggo01
 */
public abstract class SlideItem implements JabberDrawable {

    public static final String TEXT = "text";
    public static final String IMAGE = "image";

    private Vector<SlideItem> children;
    protected ItemStyle itemStyle;
    private int level;

    public SlideItem() {
        this.children = new Vector<>();
    }

    public void setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
    }

    abstract public String getContent();

    abstract public void setContent(String content);

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void addChild(SlideItem slideItem) {
        this.children.add(slideItem);
    }

    public boolean isRootItem() {
        return this.getLevel() == 1;
    }

    @Override
    public void draw(Graphics g, Rectangle area) {
        this.drawContent(g, area);
        area.y += this.getHeight(g, area);
        for (SlideItem item : this.children) {
            item.draw(g, area);
        }
    }

    /**
     * Helper method to calculate the scale of the image to fit within the area.
     * @param area
     * @return
     */
    protected float getScale(Rectangle area) {
        return Math.min(((float)area.width) / ((float) JabberPointFrame.WIDTH), ((float)area.height) / ((float) JabberPointFrame.HEIGHT));
    }

    abstract void drawContent(Graphics g, Rectangle area);
    abstract int getHeight(Graphics g, Rectangle area);
}
