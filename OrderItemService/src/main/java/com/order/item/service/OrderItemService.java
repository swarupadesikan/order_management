package com.order.item.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.order.item.common.ErrorConstants;
import com.order.item.exception.ErrorDetails;
import com.order.item.exception.ItemNotFoundException;
import com.order.item.exception.StockNotAvailableException;
import com.order.item.model.OrderItem;
import com.order.item.model.request.OrderItemUpdateRequest;
import com.order.item.repository.OrderItemRepository;

@Service
public class OrderItemService {

	@Autowired
	OrderItemRepository ordItemRep;

	public List<OrderItem> getOrderItems() {
		return ordItemRep.findAll();

	}

	public OrderItem getOrderItem(String productCode) {

		return ordItemRep.findById(productCode)
				.orElseThrow(() -> new ItemNotFoundException(new ErrorDetails(ErrorConstants.ITEMNOTFOUND_ERROR_CODE,
						ErrorConstants.ITEMNOTFOUND_ERROR + productCode)));

	}

	public String createOrder(OrderItem item) {

		ordItemRep.save(item);
		return "Order Item created";
	}

	public double placeOrder(List<String> productCodes, List<OrderItemUpdateRequest> requiredItems) {
		checkAvailability(productCodes);
		// check stock
		Map<String, Integer> orderMap = new HashMap<String, Integer>();

		for (OrderItemUpdateRequest rt : requiredItems) {
			orderMap.put(rt.getProductCode(), rt.getQuantity());
		}
		StringBuffer sbErr = new StringBuffer(ErrorConstants.STOCK_ERROR);
		List<OrderItem> items = ordItemRep.findByProductCodes(productCodes);
		boolean isStockNotAvail = false;
		double totalCost = 0;
		for (OrderItem ot : items) {
			int reqQty = orderMap.get(ot.getProductCode());
			int remainingQty = ot.getQuantity() - reqQty;
			if (remainingQty > 0) {
				ot.setQuantity(remainingQty);
				totalCost += ot.getCost() * reqQty;
			} else {
				isStockNotAvail = true;
				sbErr.append(ot.getProductCode());
				sbErr.append(ErrorConstants.AVAIL_QTY);
				sbErr.append(ot.getQuantity());
				sbErr.append(ErrorConstants.PERIOD);
			}
		}
		if (isStockNotAvail)
			throw new StockNotAvailableException(new ErrorDetails(ErrorConstants.STOCK_ERROR_CODE, sbErr.toString()));

		ordItemRep.saveAll(items);

		return totalCost;
	}

	public List<String> getOrderItems(List<String> productCodes) {

		return ordItemRep.findProdCodByProductCodes(productCodes);

	}

	public void checkAvailability(List<String> productCodes) {
		List<String> inventoryList = getOrderItems(productCodes);
		List<String> diff = productCodes.stream().filter(e -> !inventoryList.contains(e)).collect(Collectors.toList()); // (3)
		if (!CollectionUtils.isEmpty(diff)) {
			throw new ItemNotFoundException(new ErrorDetails(ErrorConstants.ITEMNSOTFOUND_ERROR_CODE,
					ErrorConstants.ITEMSNOTFOUND_ERROR + diff));
		}
	}
}
