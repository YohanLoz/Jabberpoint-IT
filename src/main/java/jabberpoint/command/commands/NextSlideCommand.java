package jabberpoint.command.commands;

import jabberpoint.presentationComponents.Presentation;

public class NextSlideCommand extends Command {

    private Presentation presentation;

    public NextSlideCommand(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        presentation.nextSlide();
    }

}
