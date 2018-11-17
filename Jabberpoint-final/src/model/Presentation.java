package model;

import view.JabberDrawable;
import view.JabberPointComponent;

import java.awt.*;
import java.awt.image.ImageObserver;
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
        if(!this.getCurrentSlide().nextItem()) {
            return this.nextSlide();
        }
        return true;
    }

    public boolean previousItem() {
        if(!this.getCurrentSlide().previousItem()) {
            return this.previousSlide();
        }
        return true;
    }

    public boolean nextSlide() {
        if (this.currentSlideNumber == this.slides.size() -1) {
            return false; // Do not add, because it is the last slide. Return false
        }
        this.currentSlideNumber++;
        return true;
    }

    public boolean previousSlide() {
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

    @Override
    public void draw(Graphics g, Rectangle area) {
        this.getCurrentSlide().draw(g, area);
    }
}
