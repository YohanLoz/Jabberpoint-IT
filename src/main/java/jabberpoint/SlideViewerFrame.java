package jabberpoint;

import jabberpoint.command.KeyController;
import jabberpoint.command.MenuController;
import jabberpoint.presentationComponents.Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * <p>The application window for a slideviewcomponent</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerFrame extends JFrame {
    private static final long serialVersionUID = 3227L;

    private static final String JABTITLE = "Jabberpoint 1.6 - OU";
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;

    public SlideViewerFrame(String title, Presentation presentation) {
        super(title);
        SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
        presentation.setShowView(slideViewerComponent);
        setupWindow(slideViewerComponent, presentation);
    }

    // Setup GUI
    public void setupWindow(SlideViewerComponent
                                    slideViewerComponent, Presentation presentation) {
        setTitle(JABTITLE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        getContentPane().add(slideViewerComponent);
        addKeyListener(new KeyController(presentation)); // add a controller
        setMenuBar(new MenuController(this, presentation));    // add another controller
        setSize(new Dimension(WIDTH, HEIGHT)); // Same sizes as presentationComponents.Slide has.
        setVisible(true);
    }
}
