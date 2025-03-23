package jabberpoint.command.commands;

import jabberpoint.presentationComponents.Presentation;

import java.awt.*;

public class ClearPresentationCommand extends Command{

    protected Frame parent;
    protected Presentation presentation;

    public ClearPresentationCommand(Frame parent, Presentation presentation)
    {
        this.parent = parent;
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        presentation.clear();
        parent.repaint();
    }

}
