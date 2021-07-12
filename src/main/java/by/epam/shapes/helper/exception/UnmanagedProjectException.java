package by.epam.shapes.helper.exception;

public class UnmanagedProjectException extends RuntimeException {
    public UnmanagedProjectException() {
    }

    public UnmanagedProjectException(String message) {
        super(message);
    }

    public UnmanagedProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnmanagedProjectException(Throwable cause) {
        super(cause);
    }
}
