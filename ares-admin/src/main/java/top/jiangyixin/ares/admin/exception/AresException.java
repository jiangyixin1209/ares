package top.jiangyixin.ares.admin.exception;

/**
 * Ares 异常
 *
 * @author jiangyixin
 * @version 1.0
 * @date 2020/12/18
 */
public class AresException extends RuntimeException {

    public AresException(String message) {
        super(message);
    }

    public AresException(Throwable e) {
        super(e);
    }

    public AresException(String message, Throwable e) {
        super(message, e);
    }
}
