package model;

import controllers.JabberObservable;

import java.util.Vector;

/**
 * Created by ggo01
 */
public class Presentation extends JabberObservable {

    private Slide currentSlide;

    private Vector<Slide> slides;

    public Presentation() {
        this.slides = new Vector<>();
    }

    public void nextItem() {
        this.currentSlide.nextItem();
        this.notifyObservers();
    }

    public void previousItem() {
        this.currentSlide.previousItem();
        this.notifyObservers();
    }
}
