package com.order.item.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Entity
public class OrderItem {
	@Id
	@NotNull(message="Product Code should not be empty")
	@Length(min = 1, max = 20, message = "Product Code is required and max length is 20" )
	private String productCode;
	
	@NotNull(message="Product Name should not be empty")
	@Length(min = 1, max = 100, message = "Product Name is required and max length is 100" )
	private String productName;
	
	@Range(min=1, max = 10000, message="Quantity should be between 1 to 10000")
	@Digits(integer=5, message="Quantity should be numeric", fraction = 0)
	private Integer quantity;
	
	@Range(min=1, message="Cost should be > $1")
	@Digits(integer=5, message="Cost should be numeric", fraction = 2)
	private Double cost;

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
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
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

	/**
	 * @return the cost
	 */
	public Double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(Double cost) {
		this.cost = cost;
	}

}
