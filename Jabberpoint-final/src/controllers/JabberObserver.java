package controllers;

/**
 * Created by ggo01
 * Observer interface that is required by any class that wishes to observer the observable.
 */
public interface JabberObserver {

    void jabberUpdate(JabberObservable observable);
}
