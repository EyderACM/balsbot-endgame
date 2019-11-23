
package exception;

public class TooLongException extends Exception {

    /**
     * Creates a new instance of <code>TooLongException</code> without detail
     * message.
     */
    public TooLongException() {
    }

    /**
     * Constructs an instance of <code>TooLongException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public TooLongException(String msg) {
        super(msg);
    }
}
