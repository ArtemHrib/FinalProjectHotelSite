package ua.nure.hrybeniuk.finalProject.exception;

/**
 * An exception that provides information on an application error.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class AppException extends Exception {

	private static final long serialVersionUID = -7002415037255056432L;

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}

}
