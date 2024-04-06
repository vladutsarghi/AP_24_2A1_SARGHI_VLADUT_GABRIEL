package myException;

public class NonExistentCommand extends Exception {
    public NonExistentCommand() {
        super();
    }

    public NonExistentCommand(String message) {
        super(message);
    }

}
