
package exception;


public class UserNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>UserNotFound</code> without detail
     * message.
     */
    public UserNotFoundException() {
    }

    /**
     * Constructs an instance of <code>UserNotFound</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
