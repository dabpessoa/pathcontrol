package br.com.digitoglobal.service.exceptions;

/**
 * Created by diego.pessoa on 16/08/2017.
 */
public class EmptyBasePathException extends PathControlRuntimeException {

    public EmptyBasePathException() {
    }

    public EmptyBasePathException(String message) {
        super(message);
    }

    public EmptyBasePathException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyBasePathException(Throwable cause) {
        super(cause);
    }

    public EmptyBasePathException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
