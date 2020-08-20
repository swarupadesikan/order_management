package com.order.customer.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.order.customer.common.Constants;
import com.order.customer.common.ErrorConstants;
import com.order.customer.exception.BaseException;
import com.order.customer.exception.ErrorDetails;
import com.order.customer.exception.OrderNotFoundException;
import com.order.customer.model.CustomerOrderDetails;
import com.order.customer.model.request.OrderItemUpdateRequest;
import com.order.customer.model.CustomerItemOrder;
import com.order.customer.repository.CustomerOrderDetailsRepository;


@Service
public class OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

	private static final String ResponseBody = null;

	@Autowired
	CustomerOrderDetailsRepository custRep;

	@Autowired
	ItemServiceClient isc;

	public List<CustomerOrderDetails> getCustomerOrders() {
		return custRep.findAll();

	}

	public CustomerOrderDetails getCustomerOrderDetail(Integer orderId) {

		return custRep.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException(new ErrorDetails(ErrorConstants.ORDERNOTFOUND_ERROR_CODE,
						ErrorConstants.ORDERNOTFOUND_ERROR + orderId)));

	}

	public String createOrder(CustomerOrderDetails cust) {
		logger.info("Started creating order");
		List<String> productCodes = new ArrayList<String>();
		List<CustomerItemOrder> orders = cust.getOrderItems();
		List<OrderItemUpdateRequest> ordersReq = new ArrayList<OrderItemUpdateRequest>();
		orders.forEach(order -> {
			order.setCustOrderItem(cust);
			order.getOrder().setProductCode(order.getProductCode());
			OrderItemUpdateRequest ot = new OrderItemUpdateRequest();
			ot.setProductCode(order.getProductCode());
			ot.setQuantity(order.getQuantity());
			ordersReq.add(ot);
			productCodes.add(order.getProductCode());
		});

		double result = isc.checkAvailabilityAndPlaceOrder(productCodes, ordersReq);
		cust.setBilledAmount(result);

		// convert order date string to date format
		try {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
			cust.setOrderDate(LocalDate.parse(cust.getOrderDateStr(),dateFormatter));
		}catch(DateTimeException e) {
			throw new BaseException(
					new ErrorDetails(ErrorConstants.DATEFORMAT_ERROR_CODE, ErrorConstants.DATEFORMAT_ERROR));
		}
		custRep.save(cust);
		StringBuffer sb = new StringBuffer(Constants.ORDER_CREATED);
		sb.append(cust.getOrderId());
		logger.info("Finished creating order");
		return sb.toString();
	}

}
