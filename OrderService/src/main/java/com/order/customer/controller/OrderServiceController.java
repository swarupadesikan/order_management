package com.order.customer.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.customer.model.CustomerOrderDetails;
import com.order.customer.model.CustomerItemOrder;
import com.order.customer.service.OrderService;
import com.order.customer.util.ResponseUtil;

@RestController
@RequestMapping("orders")
public class OrderServiceController {

	@Autowired
	OrderService orderService;

	@GetMapping
	public ResponseEntity<Object> getCustomersDetails() {
		return ResponseUtil.buildSuccessResponse(orderService.getCustomerOrders());
	}

	@GetMapping(path = "/{orderId}")
	public ResponseEntity<Object> getOrderDetails(@PathVariable Integer orderId) {
		return ResponseUtil.buildSuccessResponse(orderService.getCustomerOrderDetail(orderId));
	}

	@PostMapping
	public ResponseEntity<Object> createCustomerOrder(@Valid @RequestBody CustomerOrderDetails cust) {
		return ResponseUtil.buildSuccessResponse(orderService.createOrder(cust));
	}

}
