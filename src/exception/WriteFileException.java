package exception;

@SuppressWarnings("serial")
public class WriteFileException extends Exception {

	public WriteFileException() {
		super("Could not write to file. Please try again.");
	}

	public WriteFileException(String message) {
		super(message);
	}
}
