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
	
	@Test
	public void readExcelTest() {
		rWExcelOrder = new RWExcelOrder();
		List<Order> orders = rWExcelOrder.readExcel("./src/test/resources/excel/order.xlsx");
		assertNotNull(orders);
	}
	
	@Test
	public void readExcelTestException() {
		rWExcelOrder = new RWExcelOrder();
		List<Order> orders = rWExcelOrder.readExcel("./src/main/resources/excel/nofile.xlsx");
		assertEquals(null, orders);

	}
	@Test
	public void writeExcelTest() {
		rWExcelOrder = new RWExcelOrder();
		String writeExcelResponse = rWExcelOrder.writeExcel(getDummyOrder(), "./src/main/resources/excel/order.xlsx").getOrderId();
		assertEquals("123", writeExcelResponse);
	}
	
	@Test ( expected = NoSuchFileException.class)
	public void writeExcelException() throws NoSuchFileException {
		String filePath = "./src/main/resources/excel/nofile.xlsx";
		rWExcelOrder = new RWExcelOrder();
		rWExcelOrder.writeExcel(null, filePath);
		throw new NoSuchFileException(filePath);
	}
	
	@Test
	public void cancelOrderTest() {
		rWExcelOrder = new RWExcelOrder();
		String cancelOrderResponse = rWExcelOrder.cancelOrder(getDummyOrder().getOrderId(), "./src/main/resources/excel/order.xlsx");
		assertEquals("123", cancelOrderResponse);
	}
	
	@Test
	public void cancelOrderTestNoFile() {
		rWExcelOrder = new RWExcelOrder();
		rWExcelOrder.cancelOrder(getDummyOrder().getOrderId(), "./src/main/resources/excel/nofile.xlsx");
	}
	
	@Test
	public void getOrderByIdTest() {
		rWExcelOrder = new RWExcelOrder();
		Order orderById = rWExcelOrder.getOrderById(getDummyOrder().getOrderId(), "./src/main/resources/excel/order.xlsx");
		assertNotEquals(getDummyOrder().getOrderId(), orderById);
	}
	
	@Test
	public void writeOrderExcelTest() {
		rWExcelOrder = new RWExcelOrder();
		String response = rWExcelOrder.writeOrderExcel(getDummyOrder(), "./src/main/resources/excel/order.xlsx");
		assertNotEquals("User Order Placed Successfully!!! ", response);
	}
	@Test
	public void writeOrderExcelExceptionTest() {
		rWExcelOrder = new RWExcelOrder();
		String response = rWExcelOrder.writeOrderExcel(getDummyOrder(), "./src/main/resources/excel/nofile.xlsx");
		String expected = "NoSuchFileException";
		assertNotEquals(expected, response);
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
