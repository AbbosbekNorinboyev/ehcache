package uz.brb.ehcache.exception;

public class InvalidHeadersException extends RuntimeException {
    public InvalidHeadersException(String message) {
        super(message);
    }

    public InvalidHeadersException(String message, Throwable cause) {
        super(message, cause);
    }
}