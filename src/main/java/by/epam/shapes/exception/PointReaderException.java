package by.epam.shapes.exception;

public class PointReaderException extends Exception {
    public PointReaderException() {
        super();
    }

    public PointReaderException(String message) {
        super(message);
    }

    public PointReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public PointReaderException(Throwable cause) {
        super(cause);
    }
}
