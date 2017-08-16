package br.com.digitoglobal.service.exceptions;

/**
 * Created by diego.pessoa on 16/08/2017.
 */
public class EmptyPathException extends PathControlRuntimeException {

    public EmptyPathException() {
    }

    public EmptyPathException(String message) {
        super(message);
    }

    public EmptyPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyPathException(Throwable cause) {
        super(cause);
    }

    public EmptyPathException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
