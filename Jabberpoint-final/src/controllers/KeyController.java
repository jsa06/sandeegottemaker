package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 2.0 2018/11/18 Gerralt Gottemaker en Jan Jaap Sandee
 * Changed all commands to relay commands to the JabberPointFacade.
 * Basic structure based on original version.
*/

public class KeyController extends KeyAdapter {

	private JabberPointFacade jabberPointFacade;

	public KeyController() {
		this.jabberPointFacade = JabberPointFacade.getInstance();
	}

	public void keyPressed(KeyEvent keyEvent) {
		switch(keyEvent.getKeyCode()) {
			case KeyEvent.VK_PAGE_DOWN:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_ENTER:
			case '+':
				jabberPointFacade.nextSlideItem();
				break;
			case KeyEvent.VK_RIGHT:
				jabberPointFacade.nextSlide();
				break;
			case KeyEvent.VK_PAGE_UP:
			case KeyEvent.VK_UP:
			case '-':
				jabberPointFacade.previousSlideItem();
				break;
			case KeyEvent.VK_LEFT:
				jabberPointFacade.previousSlide();
				break;
			case 'q':
			case 'Q':
				System.exit(0);
				break; // wordt nooit bereikt als het goed is
			default:
				break;
		}
	}
}
