package com.order.customer.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CustomerItemOrder implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CustomerOrder order = new CustomerOrder();
	
	@Range(min = 1, max = 10000, message = "Quantity should be between 1 to 10000")
	@Digits(integer = 5, message = "Quantity should be numeric", fraction = 0)
	Integer quantity;
	
	@MapsId("orderId")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	@JsonIgnore
	private CustomerOrderDetails custOrderItem;
	
	@Transient
	private String productCode;

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
	 * @return the order
	 */
	public CustomerOrder getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(CustomerOrder order) {
		this.order = order;
	}

	/**
	 * @return the custOrderItem
	 */
	public CustomerOrderDetails getCustOrderItem() {
		return custOrderItem;
	}

	/**
	 * @param custOrderItem the custOrderItem to set
	 */
	public void setCustOrderItem(CustomerOrderDetails custOrderItem) {
		this.custOrderItem = custOrderItem;
	}

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

}
