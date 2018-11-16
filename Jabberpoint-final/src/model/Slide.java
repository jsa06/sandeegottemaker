package model;

import java.util.Vector;

/**
 * Created by ggo01
 */
public class Slide {

    private String title;

    private Vector<SlideItem> slideItems;

    private int currentlyVisibleIndex;

    public Slide() {
        this.slideItems = new Vector<>();
    }

    public void nextItem() {
        if (this.currentlyVisibleIndex == this.slideItems.size()) {
            // All items are already displayed. Relay this to the Presentation with a custom Exception to tell it
        }
        this.currentlyVisibleIndex++;
    }

    public void previousItem() {
        this.currentlyVisibleIndex--;
    }
}
