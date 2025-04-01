package jabberpoint.command.commands;

import jabberpoint.Accessor;
import jabberpoint.XMLAccessor;
import jabberpoint.presentationComponents.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class OpenPresentationCommand extends Command
{

    private Frame parent;
    private Presentation presentation;

    public OpenPresentationCommand(Frame parent, Presentation presentation)
    {
        this.parent = parent;
        this.presentation  = presentation;
    }

    @Override
    public void execute() {
        presentation.clear();
        Accessor xmlAccessor = new XMLAccessor();
        try {
            String TESTFILE = "dump.xml";
            xmlAccessor.loadFile(presentation, TESTFILE);
            presentation.setSlideNumber(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, IOEX + exc,
                    LOADERR, JOptionPane.ERROR_MESSAGE);
        }
        parent.repaint();
    }

}
