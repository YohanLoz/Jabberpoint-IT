package jabberpoint.command.commands;

import jabberpoint.presentationComponents.Presentation;

public class ExitCommand extends Command {

    private Presentation presentation;

    public ExitCommand(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        presentation.exit(0);
    }

}
