package controllers;

/**
 * Created by ggo01
 */
public class JabberPointFacade {

    private static JabberPointFacade instance;

    public static JabberPointFacade getInstance() {
        if (instance == null) {
            instance = new JabberPointFacade();
        }

        return instance;
    }

    private JabberPointFacade() {

    }

    public void openPresentation() {

    }

    public void startNewPresentation() {

    }

    public void savePresentation() {

    }

    public void nextSlideItem() {

    }

    public void previousSlideItem() {

    }

    public void displayAllSlideItems() {

    }

    public void hideAllSlideItems() {

    }

    public void navigateToSlide(int slideNumber) {

    }

    public String getAboutInformation() {
        return "JabberPoint by Gerralt and Jan Jaap";
    }
}
