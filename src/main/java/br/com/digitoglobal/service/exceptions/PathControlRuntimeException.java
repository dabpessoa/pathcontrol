package br.com.digitoglobal.service.exceptions;

/**
 * Created by diego.pessoa on 16/08/2017.
 */
public class PathControlRuntimeException extends RuntimeException {

    public PathControlRuntimeException() {
    }

    public PathControlRuntimeException(String message) {
        super(message);
    }

    public PathControlRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PathControlRuntimeException(Throwable cause) {
        super(cause);
    }

    public PathControlRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
