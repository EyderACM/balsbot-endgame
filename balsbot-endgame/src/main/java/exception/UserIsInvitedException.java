
package exception;

public class UserIsInvitedException extends Exception {

    /**
     * Creates a new instance of <code>UserIsInvited</code> without detail
     * message.
     */
    public UserIsInvitedException() {
    }

    /**
     * Constructs an instance of <code>UserIsInvited</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public UserIsInvitedException(String msg) {
        super(msg);
    }
}
