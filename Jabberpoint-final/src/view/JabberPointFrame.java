package view;

import controllers.KeyController;
import controllers.MenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by ggo01
 * This is the primary display frame that sets up all the required sub-components that make up the GUI.
 */
public class JabberPointFrame extends JFrame {
    private static final long serialVersionUID = 3227L;

    private static final String JABTITLE = "Jabberpoint 2.0 - OU and Gerralt + Jan Jaap";
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;

    public JabberPointFrame() {
        super(JABTITLE);
        JabberPointComponent jabberPointComponent = new JabberPointComponent(this);
        setupWindow(jabberPointComponent);
    }

    /**
     * The method to help setup the GUI
     * @param jabberPointComponent The Component that fills this Frame
     */
    private void setupWindow(JabberPointComponent jabberPointComponent) {
        setTitle(JABTITLE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        getContentPane().add(jabberPointComponent);
        addKeyListener(new KeyController());
        setMenuBar(new MenuController(this));
        setSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);
    }
}
