package com.order.item.controller;

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

import com.order.item.model.OrderItem;
import com.order.item.model.request.OrderItemUpdateRequest;
import com.order.item.service.OrderItemService;
import com.order.item.util.ResponseUtil;

@RestController
@RequestMapping("items")
public class OrderItemController {
	
	@Autowired
	OrderItemService orderService;
	
	@GetMapping
	public ResponseEntity<Object> getOrderDetails() {
		return ResponseUtil.buildSuccessResponse(orderService.getOrderItems());
	}
	

	@GetMapping(path = "/{productCode}")
	public ResponseEntity<Object> getOrderDetails(@PathVariable String productCode) {
		return ResponseUtil.buildSuccessResponse(orderService.getOrderItem(productCode));
	}
	
	@PostMapping
	public ResponseEntity<Object> createOrderDetails(@Valid @RequestBody OrderItem item) {
		return ResponseUtil.buildSuccessResponse(orderService.createOrder(item));
	}
	
	@PutMapping(path = "/{productCodes}")
	public double checkAvailabilityAndPlaceOrder(@PathVariable List<String> productCodes, @Valid @RequestBody List<@Valid OrderItemUpdateRequest> ots) {
		return orderService.placeOrder(productCodes,ots);
	}
}
