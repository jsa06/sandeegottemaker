package model.item;

import model.ItemStyle;
import view.JabberDrawable;
import view.JabberPointFrame;
import view.strategies.DrawStrategy;

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
    protected int height;

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
    public void draw(DrawStrategy strategy) {
        this.drawContent(strategy);
        for (SlideItem item : this.children) {
            item.draw(strategy);
        }
    }

    public Vector<SlideItem> getChildren() {
        return children;
    }

    abstract void drawContent(DrawStrategy strategy);
}
