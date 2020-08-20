package com.order.customer.service;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.order.customer.model.request.OrderItemUpdateRequest;


@FeignClient(name = "itemservice")
public interface ItemServiceClient {
	@PutMapping("/items/{productCodes}")
	public double checkAvailabilityAndPlaceOrder(@PathVariable List<String> productCodes, @RequestBody List<OrderItemUpdateRequest> ot);
	 
}
