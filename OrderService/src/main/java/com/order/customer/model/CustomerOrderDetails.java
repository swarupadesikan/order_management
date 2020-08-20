package com.order.customer.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Entity
public class CustomerOrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;

	@NotNull(message = "Customer Name should not be empty")
	@Length(min = 1, max = 100, message = "Customer Name is required and max length is 100")
	private String customerName;

	@NotNull(message = "Status date is a required field")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}", message = "Invalid order date")
	@Transient
	private String orderDateStr;

	@NotNull(message = "AddressLine1 should not be empty")
	@Length(min = 1, max = 100, message = "AddressLine1 is required and max length is 100")
	private String addressLine1;

	@Length(max = 100, message = "City max length is 100")
	private String city;

	@Length(max = 100, message = "State is required and max length is 100")
	private String state;

	@Range(min = 10000, max = 99999, message = "Zip should be between 10000 to 99999")
	private Integer zip;

	private Double billedAmount;

	@OneToMany(mappedBy = "custOrderItem", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<CustomerItemOrder> orderItems = new ArrayList<CustomerItemOrder>();

	private LocalDate orderDate;

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public Integer getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(Integer zip) {
		this.zip = zip;
	}

	/**
	 * @return the billedAmount
	 */
	public Double getBilledAmount() {
		return billedAmount;
	}

	/**
	 * @param billedAmount the billedAmount to set
	 */
	public void setBilledAmount(Double billedAmount) {
		this.billedAmount = billedAmount;
	}

	/**
	 * @return the orderItems
	 */

	public List<CustomerItemOrder> getOrderItems() {
		return orderItems;
	}

	/**
	 * @param orderItems the orderItems to set
	 */
	public void setOrderItems(List<CustomerItemOrder> orderItems) {
		this.orderItems = orderItems;
	}

	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the orderDateStr
	 */
	public String getOrderDateStr() {
		return orderDateStr;
	}

	/**
	 * @param orderDateStr the orderDateStr to set
	 */
	public void setOrderDateStr(String orderDateStr) {
		this.orderDateStr = orderDateStr;
	}

	/**
	 * @param localDate the orderDate to set
	 */
	public void setOrderDate(LocalDate localDate) {
		this.orderDate = localDate;
	}

	/**
	 * @return the orderDate
	 */
	public LocalDate getOrderDate() {
		return orderDate;
	}

}
