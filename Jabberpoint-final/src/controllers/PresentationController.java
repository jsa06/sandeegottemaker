package controllers;

import model.Presentation;
import view.strategies.DrawStrategy;

import java.awt.*;

/**
 * Created by ggo01
 * Main Presentation Controller which is also the observable object.
 * It tracks if anything changes in the presentation and sends the correct notifications.
 */
public class PresentationController extends JabberObservable {

    private Presentation presentation;

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
        this.notifyObservers();
    }

    public Presentation getPresentation() {
        return presentation;
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

    public boolean navigateToSlide(int slideNumber) {
        if (this.presentation != null) {
            if (this.presentation.navigateToSlide(slideNumber)) {
                this.notifyObservers();
                return true;
            }

        }

        return false;
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

    public void drawUI(DrawStrategy strategy) {
        if (this.presentation != null) {
            this.presentation.draw(strategy);
        }
    }

    public String getTitle() {
        if (this.presentation != null) {
            return this.presentation.getTitle();
        }
        return "No presentation set";
    }
}
