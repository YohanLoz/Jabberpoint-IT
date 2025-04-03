package jabberpoint.command;

import jabberpoint.command.commands.Command;
import jabberpoint.command.commands.ExitCommand;
import jabberpoint.command.commands.NextSlideCommand;
import jabberpoint.command.commands.PreviousSlideCommand;
import jabberpoint.presentationComponents.Presentation;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * <p>This is the KeyController (KeyListener)</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class KeyController extends KeyAdapter {

    private final Command nextSlideCommand;
    private final Command previousSlideCommand;
    private final Command exitCommand;

    public KeyController(Presentation p) {
        nextSlideCommand = new NextSlideCommand(p);
        previousSlideCommand = new PreviousSlideCommand(p);
        exitCommand = new ExitCommand(p);
    }

    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_PAGE_DOWN:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_ENTER:
            case '+':
                nextSlideCommand.execute();
                break;

            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_LEFT:
            case '-':
                previousSlideCommand.execute();
                break;

            case KeyEvent.VK_ESCAPE:
            case 'q':
            case 'Q':
                exitCommand.execute();
                break; // Probably never reached!!

            default:
                break;
        }
    }
}
