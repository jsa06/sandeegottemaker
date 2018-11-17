package view;

import controllers.JabberObservable;
import controllers.JabberObserver;
import controllers.JabberPointFacade;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ggo01
 */
public class JabberPointComponent extends JComponent implements JabberObserver {

    private final JabberPointFrame frame;
    private JabberPointFacade jabberPointFacade;
    private JabberObservable observable;
    private Font font;

    private static final Color BGCOLOR = Color.white;
    private static final Color COLOR = Color.black;
    private static final String FONTNAME = "Dialog";
    private static final int FONTSTYLE = Font.BOLD;
    private static final int FONTHEIGHT = 10;
    private static final int XPOS = 1100;
    private static final int YPOS = 20;

    public JabberPointComponent(JabberPointFrame frame) {
        setBackground(BGCOLOR);
        this.frame = frame;
        this.font = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
        this.jabberPointFacade = JabberPointFacade.getInstance();
        this.jabberPointFacade.addObserverToPresentationController(this);

        System.out.println("created JPComponent");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(JabberPointFrame.WIDTH, JabberPointFrame.HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g) {
        g.setColor(BGCOLOR);
        g.fillRect(0, 0, getSize().width, getSize().height);

        g.setFont(font);
        g.setColor(COLOR);
        g.drawString("Slide " + (1 + jabberPointFacade.getCurrentSlideNumber()) + " of " +
                jabberPointFacade.getNumberOfSlides(), XPOS, YPOS);
        Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
        jabberPointFacade.redrawUI(g, area);
    }

    @Override
    public void jabberUpdate(JabberObservable observable) {
        System.out.println("JabberUpdate called!");
        this.observable = observable;
        repaint();
    }
}
