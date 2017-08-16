package br.com.digitoglobal.service.exceptions;

/**
 * Created by diego.pessoa on 16/08/2017.
 */
public class PathControlException extends Exception {

    public PathControlException() {
    }

    public PathControlException(String message) {
        super(message);
    }

    public PathControlException(String message, Throwable cause) {
        super(message, cause);
    }

    public PathControlException(Throwable cause) {
        super(cause);
    }

    public PathControlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
