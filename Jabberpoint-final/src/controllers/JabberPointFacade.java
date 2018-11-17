package controllers;

import filehandlers.FileHandler;
import view.strategies.DrawStrategy;

import java.awt.*;
import java.io.IOException;

/**
 * Created by ggo01
 */
public class JabberPointFacade {

    private static String DEMOFILE = "test.xml";

    private static JabberPointFacade instance;

    private final PresentationController presentationController;
    private FileHandler fileHandler;

    public static JabberPointFacade getInstance() {
        if (instance == null) {
            instance = new JabberPointFacade();
        }

        return instance;
    }

    private JabberPointFacade() {
                this.fileHandler = FileHandler.getInstance();
        this.presentationController = new PresentationController();
    }

    public void openDemoPresentation() throws IOException {
        this.presentationController.setPresentation(this.fileHandler.readFile(""));
    }

    public void openPresentation() throws IOException {
        this.presentationController.setPresentation(this.fileHandler.readFile(DEMOFILE));
    }

    public void openPresentation(String filename) throws IOException {
        this.presentationController.setPresentation(this.fileHandler.readFile(filename));
    }

    public void startNewPresentation() throws IOException {
        this.presentationController.setPresentation(this.fileHandler.readFile(null));
    }

    public void savePresentation(String filename, String filetype) throws IOException {
        // NOTE: Currently the program only saves to dump.xml.
        fileHandler.saveFile(presentationController.getPresentation(), filetype, filename);
    }

    public void nextSlideItem() {
                this.presentationController.nextItem();
    }

    public void previousSlideItem() {
                this.presentationController.previousItem();
    }

    public void nextSlide() {
                this.presentationController.nextSlide();
    }

    public void previousSlide() {
                this.presentationController.previousSlide();
    }

    public boolean navigateToSlide(int slideNumber) {
        return this.presentationController.navigateToSlide(slideNumber);
    }

    public String getAboutInformation() {
        return "JabberPoint by Gerralt and Jan Jaap";
    }

    public void addObserverToPresentationController(JabberObserver observer) {
        if (this.presentationController == null) {
            return;
        }

        this.presentationController.addObserver(observer);
    }

    public int getNumberOfSlides() {
        return this.presentationController.getNumberOfSlides();
    }

    public int getCurrentSlideNumber() {
        return this.presentationController.getCurrentSlideNumber();
    }


    public void redrawUI(DrawStrategy strategy) {
                this.presentationController.drawUI(strategy);
    }

    public String getTitle() {
        return this.presentationController.getTitle();
    }
}