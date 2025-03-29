package jabberpoint.command.commands;

import jabberpoint.AboutBox;

import java.awt.*;

public class DisplayAboutBoxCommand extends Command{

    private Frame parent;

    public DisplayAboutBoxCommand(Frame parent)
    {
        this.parent = parent;
    }

    @Override
    public void execute() {
        AboutBox.show(parent);
    }

}
