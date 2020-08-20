package com.order.customer.util;

public class ResponseBody {

	private Object data;
	private Object error;

	public ResponseBody(Object data, Object error) {
		this.data = data;
		this.error = error;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the error
	 */
	public Object getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(Object error) {
		this.error = error;
	}

}
