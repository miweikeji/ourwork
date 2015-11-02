package app.net;

/**
 * <pre>
 *   ROP鐨勫紓甯搞€�
 * </pre>
 *
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class RopException extends RuntimeException {
    public RopException() {
    }

    public RopException(String message) {
        super(message);
    }

    public RopException(String message, Throwable cause) {
        super(message, cause);
    }

    public RopException(Throwable cause) {
        super(cause);
    }
}
