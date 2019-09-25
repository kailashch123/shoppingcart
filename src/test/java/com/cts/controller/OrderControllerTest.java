package com.cts.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.model.Order;
import com.cts.service.impl.OrderServiceImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderControllerTest extends AbstractTest {

	@InjectMocks
	OrderController orderController;

	@Mock
	private OrderServiceImpl orderService;

	@Before
	@Override
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testCancelOrderSuccess() throws Exception {
		when(orderService.cancelOrder("OR-321")).thenReturn(new String("Order cancelled successfully"));
		ResponseEntity<Object> response = orderController.cancelOrder("OR-321");
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}

	@Test
	public void testCancelOrderFailure() throws Exception {
		when(orderService.cancelOrder("OR-321")).thenReturn(null);
		ResponseEntity<Object> response = orderController.cancelOrder(null);
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
	}

	@Test
	public void testGetAllOrdersSuccess() throws Exception {
		String uri = "/orders";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Order[] orderlist = super.mapFromJson(content, Order[].class);
		assertNotNull(mvcResult.getResponse());
		assertTrue(orderlist.length > 0);
	}

	@Test
	public void testGetAllOrdersFailure() throws Exception {
		String uri = "/orders";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		String content = mvcResult.getResponse().getContentAsString();
		List<Order> orders = getOrderListFromString(content);
		assertEquals(200, mvcResult.getResponse().getStatus());
		assertTrue(orders.size() > 0);
	}

	@Test
	public void testGetOrderFailure() throws Exception {
		String uri = "/orders/or-abc-123";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.NOT_FOUND.value(), status);
	}

	@Test
	public void testPlaceOrderSuccess() throws Exception {
		Order order = getTestOrder();
		when(orderService.placeOrder(order)).thenReturn(order);
		ResponseEntity<Object> response = orderController.placeOrder(order);
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}

	@Test
	public void testPlaceOrderFailure() throws Exception {
		Order order = getTestOrder();
		when(orderService.placeOrder(order)).thenReturn(null);
		ResponseEntity<Object> response = orderController.placeOrder(null);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCodeValue());
	}
	
	@Test
	public void testGetOrderById() throws Exception {
		Order order = getTestOrder();
		when(orderService.getOrderById("OR-321")).thenReturn(order);
		ResponseEntity<Order> response = orderController.getOrderById("OR-321");
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}
	
	private Order getTestOrder() {
		Order order = new Order();
		order.setOrderId("OR-321");
		order.setOrderDate("2019-08-10");
		order.setProdId("PROD-321");
		order.setUserID("USR-321");
		return order;
	}
	
	private List<Order> getOrderListFromString(String content)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Order> orderList = mapper.readValue(content, new TypeReference<List<Order>>() {
		});
		return orderList;
	}

}
