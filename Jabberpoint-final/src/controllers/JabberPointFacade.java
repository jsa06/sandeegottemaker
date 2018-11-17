package controllers;

import filehandlers.FileHandler;
import model.Presentation;
import view.JabberPointComponent;

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
        System.out.println("Facade constructor called");
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

    public void savePresentation() {

    }

    public void nextSlideItem() {
        System.out.println("Next slide item");
        this.presentationController.nextItem();
    }

    public void previousSlideItem() {
        System.out.println("Previous slide item");
        this.presentationController.previousItem();
    }

    public void nextSlide() {
        System.out.println("Next slide");
        this.presentationController.nextSlide();
    }

    public void previousSlide() {
        System.out.println("Previous slide");
        this.presentationController.previousSlide();
    }

    public void navigateToSlide(int slideNumber) {
        System.out.println("Navigate to slide with number " + slideNumber);
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


    public void redrawUI(Graphics g, Rectangle area) {
        System.out.println("Redraw!");
        this.presentationController.drawUI(g, area);
    }
}