package jabberpoint.command;

import jabberpoint.command.commands.*;
import jabberpoint.presentationComponents.Presentation;

import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** <p>The controller for the menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar {
	
	private Frame parent; // the frame, only used as parent for the Dialogs
	private Presentation presentation; // Commands are given to the presentation
	
	private static final long serialVersionUID = 227L;
	
	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN = "Open";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";

	private final Command openPresentationCommand;
	private final Command clearPresentationCommand;
	private final Command savePresentationCommand;
	private final Command exitPresentationCommand;
	private final Command nextSlideCommand;
	private final Command previousSlideCommand;
	private final Command goToSlideCommand;
	private final Command displayAboutBoxCommand;

	public MenuController(Frame frame, Presentation pres) {

		this.parent = frame;
		this.presentation = pres;

		openPresentationCommand = new OpenPresentationCommand(parent, presentation);
		clearPresentationCommand = new ClearPresentationCommand(parent, presentation);
		savePresentationCommand = new SavePresentationCommand(parent, presentation);
		exitPresentationCommand = new ExitPresentationCommand(presentation);
		nextSlideCommand = new NextSlideCommand(presentation);
		previousSlideCommand = new PreviousSlideCommand(presentation);
		goToSlideCommand = new GoToSlideCommand(presentation);
		displayAboutBoxCommand = new DisplayAboutBoxCommand(parent);

		MenuItem menuItem;
		Menu fileMenu = new Menu(FILE);

		fileMenu.add(menuItem = mkMenuItem(OPEN));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				openPresentationCommand.execute();
			}
		} );

		fileMenu.add(menuItem = mkMenuItem(NEW));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				clearPresentationCommand.execute();
			}
		});

		fileMenu.add(menuItem = mkMenuItem(SAVE));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savePresentationCommand.execute();
			}
		});

		fileMenu.addSeparator();
		fileMenu.add(menuItem = mkMenuItem(EXIT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				exitPresentationCommand.execute();
			}
		});
		add(fileMenu);

		Menu viewMenu = new Menu(VIEW);
		viewMenu.add(menuItem = mkMenuItem(NEXT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				nextSlideCommand.execute();
			}
		});

		viewMenu.add(menuItem = mkMenuItem(PREV));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				previousSlideCommand.execute();
			}
		});

		viewMenu.add(menuItem = mkMenuItem(GOTO));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				goToSlideCommand.execute();
			}
		});
		add(viewMenu);

		Menu helpMenu = new Menu(HELP);
		helpMenu.add(menuItem = mkMenuItem(ABOUT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				displayAboutBoxCommand.execute();
			}
		});
		setHelpMenu(helpMenu);		// needed for portability (Motif, etc.).

	}

// create a menu item
	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}
