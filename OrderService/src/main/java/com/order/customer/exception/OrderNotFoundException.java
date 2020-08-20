package com.order.customer.exception;

public class OrderNotFoundException extends BaseException {
	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(ErrorDetails errorMsg) {
		super(errorMsg);
	}

}
