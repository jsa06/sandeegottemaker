package model;

import view.JabberDrawable;

import java.awt.*;
import java.util.Vector;

/**
 * Created by ggo01
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

    public boolean nextItem() {
        // Method makes use of boolean order. When first statement returns false, it goes to the next statement
        return this.getCurrentSlide().nextItem() || this.nextSlide();
    }

    public boolean previousItem() {
        // Method makes use of boolean order. When first statement returns false, it goes to the next statement
        return this.getCurrentSlide().previousItem() || this.previousSlide();
    }

    public boolean nextSlide() {
        if (this.currentSlideNumber == this.slides.size() -1) {
            return false; // Do not add, because it is the last slide. Return false
        }
        this.currentSlideNumber++;
        this.getCurrentSlide().resetSlide();
        return true;
    }

    public boolean previousSlide() {
        if (this.currentSlideNumber == 0) {
            return false; // Do not subtract, because it is the first slide. Return false
        }
        this.currentSlideNumber--;
        this.getCurrentSlide().resetSlide();
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
    public void draw(Graphics g, Rectangle area) {
        this.getCurrentSlide().draw(g, area);
    }

    public boolean navigateToSlide(int slideNumber) {
        if (slideNumber < 1 || slideNumber > this.getNumberOfSlides()) {
            return false;
        }

        this.currentSlideNumber = slideNumber -1;
        return true;
    }
}
