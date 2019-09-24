package com.cts.service.iface;

import java.util.List;

import com.cts.model.Order;

public interface IOrderService {

	Order placeOrder(Order order);

	String cancelOrder(String orderId);

	List<Order> getAllOrders();

	Order getOrderById(String orderId);

	String save(Order placeOrder);

}
