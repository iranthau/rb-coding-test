package exception;

@SuppressWarnings("serial")
public class ReadFileException extends Exception {

	public ReadFileException() {
		super("Couldn't read the file please check the path");
	}

	public ReadFileException(String message) {
		super(message);
	}
}
