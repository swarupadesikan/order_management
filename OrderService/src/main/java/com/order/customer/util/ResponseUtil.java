package com.order.customer.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.order.customer.exception.BaseException;
import com.order.customer.exception.ErrorDetails;

public class ResponseUtil {

	public static ResponseEntity buildFailedResponse(String errorCode, String errorMsg) {
		ResponseBody respBody = new ResponseBody("", new ErrorDetails(errorCode, errorMsg));
		ResponseEntity respEntity = new ResponseEntity(respBody, HttpStatus.BAD_REQUEST);
		return respEntity;

	}

	public static ResponseEntity buildFailedResponse(BaseException exception) {
		ResponseBody respBody = new ResponseBody("", exception.getErrorMsg());
		ResponseEntity respEntity = new ResponseEntity(respBody, HttpStatus.BAD_REQUEST);
		return respEntity;

	}

	public static ResponseEntity buildSuccessResponse(Object obj) {
		ResponseBody respBody = new ResponseBody(obj, "");
		return new ResponseEntity(respBody, HttpStatus.ACCEPTED);
	}

	public static ResponseEntity buildFailedResponse(Exception exception) {
		ResponseBody respBody = new ResponseBody("", exception.getMessage());
		ResponseEntity respEntity = new ResponseEntity(respBody, HttpStatus.BAD_REQUEST);
		return respEntity;

	}
}
