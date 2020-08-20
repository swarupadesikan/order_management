package com.order.item.exception;

public class StockNotAvailableException extends BaseException{
	private static final long serialVersionUID = 1L;
	public StockNotAvailableException(ErrorDetails errorMsg){
		super(errorMsg);
	}
	
}
