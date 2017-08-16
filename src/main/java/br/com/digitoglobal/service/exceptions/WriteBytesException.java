package br.com.digitoglobal.service.exceptions;

/**
 * Created by diego.pessoa on 16/08/2017.
 */
public class WriteBytesException extends PathControlRuntimeException {

    public WriteBytesException() {
    }

    public WriteBytesException(String message) {
        super(message);
    }

    public WriteBytesException(String message, Throwable cause) {
        super(message, cause);
    }

    public WriteBytesException(Throwable cause) {
        super(cause);
    }

    public WriteBytesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
