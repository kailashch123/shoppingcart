/**
 * This class is used to give the order details.
 * 
 * @author 764432
 *
 */
package com.cts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Order;
import com.cts.repository.iface.IOrderRepository;
import com.cts.repository.impl.OrderRepositoryImpl;
import com.cts.service.iface.IOrderService;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;

	/**
	 * It is used to place the order.
	 * 
	 * @param order
	 * @return
	 */
	@Override
	public Order placeOrder(Order order) {
		return orderRepository.placeOrder(order);

	}

	/**
	 * It is used to cancel the order.
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public String cancelOrder(String orderId) {
		return orderRepository.cancelOrder(orderId);
	}

	/**
	 * It is used to getting the list of orders.
	 * 
	 * @return
	 */
	@Override
	public List<Order> getAllOrders() {
		return orderRepository.getAllOrders();
	}

	/**
	 * It is used to get the product detail.
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public Order getOrderById(String orderId) {
		return orderRepository.getOrderById(orderId);
	}

	/**
	 * It is used to save the order details into excel.
	 * 
	 * @param placeOrder
	 * @return
	 */
	@Override
	public String save(Order placeOrder) {
		return orderRepository.save(placeOrder);
	}

}
