package model.item;

import model.ItemStyle;
import view.JabberDrawable;
import view.JabberPointFrame;

import java.awt.*;
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
    private int height;

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
        if (this.children.isEmpty()) {
            this.children.add(slideItem);
            return;
        }

        SlideItem lastAddedChild = this.children.get(this.children.size() -1);
        if (slideItem.getLevel() == lastAddedChild.getLevel()) {
            this.children.add(slideItem);
        } else {
            lastAddedChild.addChild(slideItem);
        }
    }

    public boolean isRootItem() {
        return this.getLevel() == 1;
    }

    @Override
    public void draw(Graphics g, Rectangle area) {
        this.height = this.drawContent(g, area);
        area.y += this.getHeight();
        for (SlideItem item : this.children) {
            item.draw(g, area);
        }
    }

    protected int getHeight() {
        return height;
    }

    public Vector<SlideItem> getChildren() {
        return children;
    }

    /**
     * Helper method to calculate the scale of the item to fit within the area.
     * @param area
     * @return
     */
    protected float getScale(Rectangle area) {
        return Math.min(((float)area.width) / ((float) JabberPointFrame.WIDTH), ((float)area.height) / ((float) JabberPointFrame.HEIGHT));
    }

    abstract int drawContent(Graphics g, Rectangle area);
}
