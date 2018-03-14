package com.xian.blog.exception;

public class FtpException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FtpException() {
		super();
	}

	public FtpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FtpException(String message, Throwable cause) {
		super(message, cause);
	}

	public FtpException(String message) {
		super(message);
	}

	public FtpException(Throwable cause) {
		super(cause);
	}

}
