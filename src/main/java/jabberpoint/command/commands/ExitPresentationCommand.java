package jabberpoint.command.commands;

import jabberpoint.presentationComponents.Presentation;

public class ExitPresentationCommand extends Command
{

    protected Presentation presentation;

    public ExitPresentationCommand(Presentation presentation)
    {
        this.presentation  = presentation;
    }

    @Override
    public void execute() {
        presentation.exit(0);
    }

}
