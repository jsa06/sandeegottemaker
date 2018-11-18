package model;

import view.JabberDrawable;
import view.strategies.DrawStrategy;

import java.awt.*;
import java.util.Vector;

/**
 * Created by ggo01
 * Presentation model object. Contains slides and commands for controlling the slides.
 */
public class Presentation implements JabberDrawable {

    private String title;

    private int currentSlideNumber;

    private Vector<Slide> slides;

    public Presentation() {
        this.slides = new Vector<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addSlide(Slide slide) {
        this.slides.add(slide);
    }

    /**
     * Show the next slide item.
     * Note: Method makes use of boolean order. When first statement returns false, it goes to the next statement
     * @return True if successful, False if at the last slide.
     */
    public boolean nextItem() {

        return this.getCurrentSlide().nextItem() || this.nextSlide();
    }

    /**
     * Hide the most recently shown slide item.
     * Note: Method makes use of boolean order. When first statement returns false, it goes to the next statement
     * @return true if succesful, false if at the first slide.
     */
    public boolean previousItem() {
        return this.getCurrentSlide().previousItem() || this.previousSlide();
    }

    /**
     * This method has two primary conditions. If not all items are visible it will show all items.
     * If all items are visible it will navigate to the next slide.
     * @return true is succesful, false if presentation is at the last slide.
     */
    public boolean nextSlide() {
        if(this.getCurrentSlide().getCurrentlyVisibleIndex() < this.getCurrentSlide().getSlideItems().size()){
            this.getCurrentSlide().setCurrentlyVisibleIndex(this.getCurrentSlide().getSlideItems().size());
            return true;
        }
        if (this.currentSlideNumber == this.slides.size() -1) {
            return false; // Do not add, because it is the last slide. Return false
        }

        this.currentSlideNumber++;
        this.getCurrentSlide().resetSlide();
        return true;
    }

    /**
     * This method has two primary conditions. If items are visible clear the slide.
     * If no items are visible move to the previous slide.
     * It also doesn't clear the slides, which means that when navigating back all items will be shown.
     * This is by design as it gives a more natural feel.
     * @return true if succesful, false if the presentation is the first slide.
     */
    public boolean previousSlide() {
        if(this.getCurrentSlide().getCurrentlyVisibleIndex() > 0){
            this.getCurrentSlide().setCurrentlyVisibleIndex(0);
            return true;
        }
        if (this.currentSlideNumber == 0) {
            return false; // Do not subtract, because it is the first slide. Return false
        }
        this.currentSlideNumber--;
        return true;
    }

    public int getNumberOfSlides() {
        return this.slides.size();
    }

    public int getCurrentSlideNumber() {
        return currentSlideNumber;
    }

    private Slide getCurrentSlide() {
        return this.slides.elementAt(this.currentSlideNumber);
    }

    public Vector<Slide> getSlides() {
        return slides;
    }

    @Override
    public void draw(DrawStrategy strategy) {
        this.getCurrentSlide().draw(strategy);
    }

    /**
     * Navigate to a specific slide.
     * Note: If the slide has been displayed previously all items will be shown.
     * If a slide has not been shown yet, it will be blank.
     * @param slideNumber
     * @return True if succesful, false if failed.
     */
    public boolean navigateToSlide(int slideNumber) {
        if (slideNumber < 1 || slideNumber > this.getNumberOfSlides()) {
            return false;
        }

        this.currentSlideNumber = slideNumber -1;
        return true;
    }
}
