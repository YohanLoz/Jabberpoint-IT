package jabberpoint.command.commands;

import jabberpoint.presentationComponents.Presentation;

public class PreviousSlideCommand extends Command{

    private Presentation presentation;

    public PreviousSlideCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        presentation.prevSlide();
    }

}
