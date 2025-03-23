package jabberpoint.command.commands;

import jabberpoint.Accessor;
import jabberpoint.XMLAccessor;
import jabberpoint.presentationComponents.Presentation;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;

public class SavePresentationCommand extends Command
{

    protected Frame parent;
    protected Presentation presentation;

    public SavePresentationCommand(Frame parent, Presentation presentation)
    {
        this.parent = parent;
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        Accessor xmlAccessor = new XMLAccessor();
        try {
            String SAVEFILE = "dump.xml";
            xmlAccessor.saveFile(this.presentation, SAVEFILE);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, IOEX + exc,
                    SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
    }

}
