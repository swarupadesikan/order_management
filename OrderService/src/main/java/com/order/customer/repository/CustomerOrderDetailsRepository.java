package com.order.customer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.order.customer.model.CustomerOrderDetails;

public interface CustomerOrderDetailsRepository extends CrudRepository<CustomerOrderDetails,Integer>{
	List<CustomerOrderDetails> findAll();

}
