package model;

import model.item.SlideItem;
import view.JabberDrawable;
import view.strategies.DrawStrategy;

import java.awt.*;
import java.util.Vector;

/**
 * Created by ggo01
 * The Slide model which contains a collection of Slide items.
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

    /**
     * Display the next level 1 item.
     * @return True if successful, false if there are no more items to show.
     */
    public boolean nextItem() {
        if (!this.transitionEnabled || this.currentlyVisibleIndex == this.slideItems.size()) {
            // All items are already displayed. Relay this to the Presentation using the boolean false (not succeeded)
            return false;
        }

        this.currentlyVisibleIndex++;
        return true;
    }

    /**
     * Remove the last level 1 item displayed.
     * @return True if successful, false if there are no items shown.
     */
    public boolean previousItem() {
        if (!this.transitionEnabled || this.currentlyVisibleIndex == 0) {
            // All items are already hidden. Relay this to the Presentation using the boolean false (not succeeded)
            return false;
        }
        this.currentlyVisibleIndex--;
        return true;
    }

    /**
     * Add a new item. If an item is level 1 then it will be added to the top.
     * Any level that is a higher number will be delegated to the last added item of a lower level.
     * This results in a nested structure for the items.
     * @param slideItem
     */
    public void addItem(SlideItem slideItem) {
        if (this.slideItems.isEmpty()) {
            this.slideItems.add(slideItem);
            return;
        }

        if(slideItem.isRootItem()) {
            this.slideItems.add(slideItem);
            return;
        }

        SlideItem lastAdded = this.slideItems.get(slideItems.size() -1);
        if (slideItem.getLevel() == lastAdded.getLevel()) {
            this.slideItems.add(slideItem);
        } else {
            lastAdded.addChild(slideItem);
        }
    }

    /**
     * Reset this slide.
     */
    public void resetSlide() {
        if (this.transitionEnabled) {
            this.currentlyVisibleIndex = 0;
        } else {
            this.currentlyVisibleIndex = this.slideItems.size();
        }
    }

    public int getCurrentlyVisibleIndex() {
        return currentlyVisibleIndex;
    }

    public void setCurrentlyVisibleIndex(int newindex) {
        this.currentlyVisibleIndex = newindex;
    }

    public void setTransitionEnabled(boolean transitionEnabled) {
        this.transitionEnabled = transitionEnabled;
        resetSlide();
    }

    public Vector<SlideItem> getSlideItems() {
        return slideItems;
    }

    @Override
    public void draw(DrawStrategy strategy) {
        this.title.draw(strategy);
        for (int i = 0; i < this.currentlyVisibleIndex; i++) {
            this.slideItems.get(i).draw(strategy);
        }
    }
}
