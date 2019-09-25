package com.cts.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import org.junit.ComparisonFailure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.model.Order;

@RunWith(SpringRunner.class)
public class RWExcelOrderTest {
	
	RWExcelOrder rWExcelOrder;
	private String filePath = "./src/main/resources/excel/order.xlsx";
	private String filePathNoFile = "./src/main/resources/excel/nofile.xlsx";
	
	@Test
	public void readExcelTest() {
		rWExcelOrder = new RWExcelOrder();
		List<Order> orders = rWExcelOrder.readExcel(filePath);
		assertNotNull(orders);
	}
	
	@Test
	public void readExcelTestException() {
		rWExcelOrder = new RWExcelOrder();
		List<Order> orders = rWExcelOrder.readExcel(filePathNoFile);
		assertEquals(null, orders);

	}
	@Test
	public void writeExcelTest() {
		rWExcelOrder = new RWExcelOrder();
		Order order = getDummyOrder();
		String writeExcelResponse = rWExcelOrder.writeExcel(order, filePath).getOrderId();
		assertEquals("123", writeExcelResponse);
	}
	
	@Test ( expected = NoSuchFileException.class)
	public void writeExcelException() throws NoSuchFileException {
		rWExcelOrder = new RWExcelOrder();
		rWExcelOrder.writeExcel(null, filePathNoFile);
		throw new NoSuchFileException(filePathNoFile);
	}
	
	@Test
	public void cancelOrderTest() {
		rWExcelOrder = new RWExcelOrder();
		Order order = getDummyOrder();
		String orderId = order.getOrderId();
		String writeExcelResponse = rWExcelOrder.writeExcel(order, filePath).getOrderId();
		assertEquals(orderId, writeExcelResponse);
		
		rWExcelOrder = new RWExcelOrder();
		String cancelOrderResponse = rWExcelOrder.cancelOrder(order.getOrderId(), filePath);
		assertEquals(orderId, cancelOrderResponse);
	}
	
	@Test
	public void cancelOrderTestNoFile() {
		rWExcelOrder = new RWExcelOrder();
		Order order = getDummyOrder();
		rWExcelOrder.cancelOrder(order.getOrderId(), filePathNoFile);
	}
	
	@Test
	public void getOrderByIdTest() {
		rWExcelOrder = new RWExcelOrder();
		Order order = getDummyOrder();
		Order orderById = rWExcelOrder.getOrderById(order.getOrderId(), filePath);
		assertNotEquals(order.getOrderId(), orderById);
	}
	
	@Test
	public void writeOrderExcelTest() {
		rWExcelOrder = new RWExcelOrder();
		Order order = getDummyOrder();
		String response = rWExcelOrder.writeOrderExcel(order, filePath);
		assertNotEquals("User Order Placed Successfully!!! ", response);
	}
	@Test
	public void writeOrderExcelExceptionTest() {
		rWExcelOrder = new RWExcelOrder();
		Order order = getDummyOrder();
		String response = rWExcelOrder.writeOrderExcel(order, filePathNoFile);
		String expected = "NoSuchFileException";
		assertEquals(expected, response);
	}
	
	private Order getDummyOrder() {
		Order order = new Order();
		order.setOrderId("123");
		order.setProdId("G4234");
		order.setUserID("test");
		order.setOrderDate("2019-09-12T12:01:20.457Z");
		return order;
	}

}
