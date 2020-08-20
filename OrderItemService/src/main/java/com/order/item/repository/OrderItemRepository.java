package com.order.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.order.item.model.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem,String>{
	List<OrderItem> findAll();
	
	@Query( "select productCode from OrderItem o where productCode in :productCodes" )
	List<String> findProdCodByProductCodes(@Param("productCodes") List<String> productCodes);
	
	@Query( "select o from OrderItem o where productCode in :productCodes" )
	List<OrderItem> findByProductCodes(@Param("productCodes") List<String> productCodes);

}
