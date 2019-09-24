package com.cts.repository.iface;

import java.util.List;

import com.cts.model.Order;

public interface IOrderRepository {
	
	Order placeOrder(Order order);

	String cancelOrder(String orderId);

	List<Order> getAllOrders();

	Order getOrderById(String orderId);

	String save(Order placeOrder);
}
