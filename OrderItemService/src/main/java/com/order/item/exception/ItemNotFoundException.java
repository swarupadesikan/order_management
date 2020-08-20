package com.order.item.exception;

public class ItemNotFoundException extends BaseException{
	private static final long serialVersionUID = 1L;
	public ItemNotFoundException(ErrorDetails errorMsg){
		super(errorMsg);
	}
	
}
