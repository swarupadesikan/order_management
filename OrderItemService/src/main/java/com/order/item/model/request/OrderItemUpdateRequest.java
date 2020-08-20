package com.order.item.model.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class OrderItemUpdateRequest {
	@NotNull(message="Product Code should not be empty")
	@Length(min = 1, max = 20, message = "Product Code is required and max length is 20" )
	String productCode;
	@Range(min=1, max = 10000, message="Quantity should be between 1 to 10000")
	@Digits(integer=5, message="Quantity should be numeric", fraction = 0)
	Integer quantity;
	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
