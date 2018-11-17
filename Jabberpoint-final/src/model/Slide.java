package model;

import model.item.SlideItem;
import model.item.TextItem;
import view.JabberDrawable;
import view.JabberPointComponent;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Vector;

/**
 * Created by ggo01
 */
public class Slide implements JabberDrawable {

    private SlideItem title;

    private Vector<SlideItem> slideItems;

    private int currentlyVisibleIndex = -1; // Set standard to minus 1 to display only the title

    public Slide() {
        this.slideItems = new Vector<>();
    }

    public String getTitle() {
        return title.getContent();
    }

    public void setTitle(SlideItem title) {
        this.title = title;
    }

    public boolean nextItem() {
        if (this.currentlyVisibleIndex == this.slideItems.size()) {
            // All items are already displayed. Relay this to the Presentation using the boolean false (not succeeded)
            return false;
        }

        this.currentlyVisibleIndex++;
        return true;
    }

    public boolean previousItem() {
        if (this.currentlyVisibleIndex == 0) {
            // All items are already hidden. Relay this to the Presentation using the boolean false (not succeeded)
            return false;
        }
        this.currentlyVisibleIndex--;
        return true;
    }

    public void addItem(SlideItem slideItem) {
        if (slideItem.isRootItem()) {
            // Add to slideItems, since it is a level 1 (root) item
            this.slideItems.add(slideItem);
        } else {
            // Add to last added slideItem, if available.
            this.slideItems.get(slideItems.size() -1).addChild(slideItem);
        }

    }

    @Override
    public void draw(Graphics g, Rectangle area) {
        this.title.draw(g, area);
        for (int i = 0; i < this.currentlyVisibleIndex; i++) {
            this.slideItems.get(i).draw(g, area);
        }
    }
}
