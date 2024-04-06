package myException;

public class IllegalCommand extends Exception {
    public IllegalCommand() {
        super();
    }

    public IllegalCommand(String message) {
        super(message);
    }

    public IllegalCommand(String message, Throwable cause) {
        super(message, cause);
    }
}

