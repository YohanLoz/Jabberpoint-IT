package jabberpoint.command.commands;

public abstract class Command {

    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Load Error";
    protected static final String SAVEERR = "Save Error";

    public Command() {
    }

    public abstract void execute();

}
