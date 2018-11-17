package controllers;

import model.Presentation;
import view.JabberDrawable;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Created by ggo01
 */
public class PresentationController extends JabberObservable {

    private Presentation presentation;

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
        this.notifyObservers();
    }

    public void nextItem() {
        if (this.presentation!= null && this.presentation.nextItem()) {
            this.notifyObservers();
        }
    }

    public void previousItem() {
        if (this.presentation != null && this.presentation.previousItem()) {
            this.notifyObservers();
        }
    }

    public void nextSlide() {
        if (this.presentation != null && this.presentation.nextSlide()) {
            this.notifyObservers();
        }
    }

    public void previousSlide() {
        if (this.presentation != null && this.presentation.previousSlide()) {
            this.notifyObservers();
        }
    }

    public int getNumberOfSlides() {
        if (this.presentation != null) {
            return this.presentation.getNumberOfSlides();
        }
        return 0;
    }

    public int getCurrentSlideNumber() {
        if (this.presentation != null) {
            return this.presentation.getCurrentSlideNumber();
        }
        return -1;
    }

    public void drawUI(Graphics g, Rectangle area) {
        if (this.presentation != null) {
            this.presentation.draw(g, area);
        }
    }
}
