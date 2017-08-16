package br.com.digitoglobal.service.exceptions;

/**
 * Created by diego.pessoa on 16/08/2017.
 */
public class InvalidPathOrderException extends PathControlRuntimeException {

    public InvalidPathOrderException() {
    }

    public InvalidPathOrderException(String message) {
        super(message);
    }

    public InvalidPathOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPathOrderException(Throwable cause) {
        super(cause);
    }

    public InvalidPathOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
