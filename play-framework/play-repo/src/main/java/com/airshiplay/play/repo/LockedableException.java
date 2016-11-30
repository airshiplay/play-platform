package com.airshiplay.play.repo;

/**
 * 
 * @author lig
 *
 */
public class LockedableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LockedableException() {
		this("cannot delete the locked entity.");
	}

	public LockedableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LockedableException(String message, Throwable cause) {
		super(message, cause);
	}

	public LockedableException(String message) {
		super(message);
	}

	public LockedableException(Throwable cause) {
		super(cause);
	}

}
