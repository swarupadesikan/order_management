package com.order.customer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.order.customer.common.ErrorConstants;
import com.order.customer.exception.BaseException;
import com.order.customer.exception.OrderNotFoundException;
import com.order.customer.util.ResponseUtil;

import feign.FeignException;


@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
		List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
		String error;
		for (FieldError fieldError : fieldErrors) {
			error = fieldError.getDefaultMessage();
			errors.add(error);
		}
		for (ObjectError objectError : globalErrors) {
			error = objectError.getDefaultMessage();
			errors.add(error);
		}

		return ResponseUtil.buildFailedResponse(ErrorConstants.VALIDATOR_ERROR_CODE, String.join(",", errors));
	}

	@ExceptionHandler(value = OrderNotFoundException.class)
	public ResponseEntity<Object> exception(OrderNotFoundException exception) {
		return ResponseUtil.buildFailedResponse(exception);
	}

	@ExceptionHandler(value = BaseException.class)
	public ResponseEntity<Object> exception(BaseException exception) {
		return ResponseUtil.buildFailedResponse(exception);
	}

	@ExceptionHandler(value = FeignException.class)
	public ResponseEntity<Object> exception(FeignException exception) {
		return ResponseUtil.buildFailedResponse(exception);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return ResponseUtil.buildFailedResponse(ErrorConstants.JSON_PARSE_ERROR_CODE, ErrorConstants.JSON_PARSE_ERROR);
	}

}
