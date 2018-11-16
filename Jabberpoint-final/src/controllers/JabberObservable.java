package controllers;

import java.util.Vector;

/**
 * Created by ggo01
 */
public abstract class JabberObservable {

    private Vector<JabberObserver> observers;

    protected JabberObservable() {
        this.observers = new Vector<>();
    }

    protected void addObserver(JabberObserver observer) {
        this.observers.add(observer);
    }

    protected void notifyObservers() {
        for (JabberObserver observer : observers) {
            observer.update(this);
        }
    }
}
