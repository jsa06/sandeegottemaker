package model;

import model.item.SlideItem;
import view.JabberDrawable;

import java.awt.*;
import java.util.Vector;

/**
 * Created by ggo01
 */
public class Slide implements JabberDrawable {

    private SlideItem title;
    private Vector<SlideItem> slideItems;
    private int currentlyVisibleIndex;
    private boolean transitionEnabled = true; //Standard enabled

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
        if (!this.transitionEnabled || this.currentlyVisibleIndex == this.slideItems.size()) {
            // All items are already displayed. Relay this to the Presentation using the boolean false (not succeeded)
            return false;
        }

        this.currentlyVisibleIndex++;
        return true;
    }

    public boolean previousItem() {
        if (!this.transitionEnabled || this.currentlyVisibleIndex == 0) {
            // All items are already hidden. Relay this to the Presentation using the boolean false (not succeeded)
            return false;
        }
        this.currentlyVisibleIndex--;
        return true;
    }

    public void addItem(SlideItem slideItem) {
        if (this.slideItems.isEmpty()) {
            // Add to slideItems, because list is currently empty.
            this.slideItems.add(slideItem);
            return;
        }

        if(slideItem.isRootItem()) {
            // Add to slideItems, because it is a level 1 (root) item
            this.slideItems.add(slideItem);
            return;
        }

        SlideItem lastAdded = this.slideItems.get(slideItems.size() -1);
        if (slideItem.getLevel() == lastAdded.getLevel()) {
            // Add to slideItems, since it has the same level. Item should live next to lastAdded, because they are siblings
            this.slideItems.add(slideItem);
        } else {
            // Add to last added slideItem, because it should be a child of that item
            lastAdded.addChild(slideItem);
        }
    }

    public void resetSlide() {
        if (this.transitionEnabled) {
            this.currentlyVisibleIndex = 0;
        } else {
            this.currentlyVisibleIndex = this.slideItems.size();
        }
    }

    public boolean isTransitionEnabled() {
        return transitionEnabled;
    }

    public void setTransitionEnabled(boolean transitionEnabled) {
        this.transitionEnabled = transitionEnabled;
        resetSlide();
    }

    @Override
    public void draw(Graphics g, Rectangle area) {
        this.title.draw(g, area);
        for (int i = 0; i < this.currentlyVisibleIndex; i++) {
            this.slideItems.get(i).draw(g, area);
        }
    }
}
