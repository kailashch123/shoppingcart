package com.cts.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.model.Order;

@RunWith(SpringRunner.class)
public class RWExcelOrderTest {
	
	@InjectMocks
	RWExcelOrder rWExcelOrder;
	
	@Test
	public void readExcelTest() {
		assertNotNull(rWExcelOrder.readExcel().size());
	}
	
	@Test
	public void writeExcelTest() {
		Order order1 = new Order();
		order1.setOrderId("123");
		order1.setProdId("G4234");
		order1.setUserID("test");
		String writeExcelResponse = rWExcelOrder.writeExcel(order1).getOrderId();
		assertEquals("123", writeExcelResponse);
	}
	
	@Test
	public void cancelOrderTest() {
		Order order1 = new Order();
		order1.setOrderId("123");
		order1.setProdId("G4234");
		order1.setUserID("test");
		String cancelOrderResponse = rWExcelOrder.cancelOrder(order1.getOrderId());
		assertEquals("123", cancelOrderResponse);
	}
	
	@Test
	public void getOrderByIdTest() {
		Order order1 = new Order();
		order1.setOrderId("123");
		order1.setProdId("G4234");
		order1.setUserID("test");
		order1.setOrderDate("2019-09-12T12:01:20.457Z");
		Order orderById = rWExcelOrder.getOrderById(order1.getOrderId());
		assertNotEquals(order1, orderById);
	}
	
	@Test
	public void writeOrderExcelTest() {
		Order order1 = new Order();
		order1.setOrderId("123");
		order1.setProdId("G4234");
		order1.setUserID("test");
		order1.setOrderDate("2019-09-12T12:01:20.457Z");
		String response = rWExcelOrder.writeOrderExcel(order1);
		assertNotEquals("User Order Placed Successfully!!! ", response);
	}

}
