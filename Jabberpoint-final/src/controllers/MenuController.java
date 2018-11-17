package controllers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/** <p>De controller voor het menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar {
	
	private Frame parent; // het frame, alleen gebruikt als ouder voor de Dialogs
	private JabberPointFacade jabberPointFacade;
	
	private static final long serialVersionUID = 227L;
	
	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to slide";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String NEXT_ITEM = "Next item";
	protected static final String OPEN = "Open";
	protected static final String PAGENR = "Page number?";
	protected static final String PREV = "Prev";
	protected static final String PREV_ITEM = "Prev item";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";

	protected static final String LOADERR = "Load Error";
	protected static final String SAVEERR = "Save Error";

	public MenuController(Frame frame) {
		this.jabberPointFacade = JabberPointFacade.getInstance();
		this.parent = frame;
		MenuItem menuItem;
		Menu fileMenu = new Menu(FILE);
		fileMenu.add(menuItem = mkMenuItem(OPEN));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					jabberPointFacade.openPresentation();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(parent, e.getMessage(),
							LOADERR, JOptionPane.ERROR_MESSAGE);
				}
			}
		} );
		fileMenu.add(menuItem = mkMenuItem(NEW));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					jabberPointFacade.startNewPresentation();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(parent, e.getMessage(),
							LOADERR, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		fileMenu.add(menuItem = mkMenuItem(SAVE));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					jabberPointFacade.savePresentation("dump", "xml");
					JOptionPane.showMessageDialog(parent, "Save has been made!",
							"Saved", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(parent, e.getMessage(),
							LOADERR, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		fileMenu.addSeparator();
		fileMenu.add(menuItem = mkMenuItem(EXIT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				System.exit(0);
			}
		});
		add(fileMenu);
		Menu viewMenu = new Menu(VIEW);

		viewMenu.add(menuItem = mkMenuItem(NEXT_ITEM));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				jabberPointFacade.nextSlideItem();
			}
		});

		viewMenu.add(menuItem = mkMenuItem(NEXT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				jabberPointFacade.nextSlide();
			}
		});

		viewMenu.add(menuItem = mkMenuItem(PREV));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				jabberPointFacade.previousSlide();
			}
		});

		viewMenu.add(menuItem = mkMenuItem(PREV_ITEM));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				jabberPointFacade.previousSlideItem();
			}
		});

		viewMenu.add(menuItem = mkMenuItem(GOTO));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String pageNumberStr = JOptionPane.showInputDialog(PAGENR);
				try {
					int pageNumber = Integer.parseInt(pageNumberStr);
					if (!jabberPointFacade.navigateToSlide(pageNumber)) {
						JOptionPane.showMessageDialog(parent, "Please enter a number between 1 and " + jabberPointFacade.getNumberOfSlides(),
								"No correct input", JOptionPane.ERROR_MESSAGE);
						actionPerformed(actionEvent);
					}
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(parent, "Please enter a number!",
							"No correct input", JOptionPane.ERROR_MESSAGE);
					actionPerformed(actionEvent);
				}

			}
		});
		add(viewMenu);
		Menu helpMenu = new Menu(HELP);
		helpMenu.add(menuItem = mkMenuItem(ABOUT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				JOptionPane.showMessageDialog(parent,
						jabberPointFacade.getAboutInformation(),
						"About JabberPoint",
						JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		setHelpMenu(helpMenu);		// nodig for portability (Motif, etc.).
	}

// een menu-item aanmaken
	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}
