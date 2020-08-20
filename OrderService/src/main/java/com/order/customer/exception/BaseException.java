package com.order.customer.exception;

public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorDetails errorMsg;

	public BaseException(ErrorDetails errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the errorMsg
	 */
	public ErrorDetails getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(ErrorDetails errorMsg) {
		this.errorMsg = errorMsg;
	}

}
