package by.epam.shapes.helper.exception;

public class UnsupportedValueException extends RuntimeException {
    public UnsupportedValueException() {
        super();
    }
    public UnsupportedValueException(String message) {
        super(message);
    }
    public UnsupportedValueException(String message, Throwable cause) {
        super(message, cause);
    }
    public UnsupportedValueException(Throwable cause) {
        super(cause);
    }
}
