import controllers.JabberPointFacade;
import view.JabberPointFrame;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by ggo01
 * Original JabberPoint program by: Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * Refactor by: Jan Jaap Sandee and Gerralt Gottemaker
 */
public class JabberPoint {

    public static void main(String[] args) {
        new JabberPointFrame();
        JabberPointFacade jabberPointFacade = JabberPointFacade.getInstance();
        try {
            if (args.length == 0) { // een demo presentatie
                jabberPointFacade.openDemoPresentation();
            } else {
                jabberPointFacade.openPresentation(args[0]);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(), ex.getClass().getCanonicalName(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
