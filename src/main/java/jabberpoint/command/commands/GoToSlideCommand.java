package jabberpoint.command.commands;

import javax.swing.*;

import jabberpoint.presentationComponents.Presentation;

public class GoToSlideCommand extends Command{

    private final String PAGENR = "Page number?";
    private Presentation presentation;

    public GoToSlideCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        String pageNumberStr = JOptionPane.showInputDialog((Object)PAGENR);
        int pageNumber = Integer.parseInt(pageNumberStr);
        presentation.setSlideNumber(pageNumber - 1);
    }

}
