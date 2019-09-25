package com.cts.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import com.cts.model.Order;
import com.cts.model.Product;
import com.cts.service.impl.OrderServiceImpl;
import com.cts.service.impl.ProductDetailsServiceImpl;

public class ProductDetailsControllerTest extends AbstractTest {

	@InjectMocks
	ProductDetailsController productDetailsController;

	@InjectMocks
	OrderController orderController;
	private String filePath = "./src/main/resources/excel/product.xlsx";
	@Mock
	private ProductDetailsServiceImpl productDetailsService;

	@Mock
	private OrderServiceImpl orderService;

	@Mock
	private RestTemplate restTemplate;

	@Before
	@Override
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testAddItem() {
		String Response = "Product Added Successfully";
		Product product = getTestProduct();
		when(productDetailsService.addItem(product, filePath)).thenReturn(Response);
		ResponseEntity<String> response = productDetailsController.addItem(product);
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}

	@Test
	public void testRemoveItemSuccess() throws Exception {
		when(productDetailsService.removeItem("PROD-12", filePath)).thenReturn("abcd");
		ResponseEntity<String> response = productDetailsController.removeItem("PROD-12");
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}

	@Test
	public void testRemoveItemFailure() throws Exception {
		when(productDetailsService.removeItem("12", filePath)).thenReturn(null);
		ResponseEntity<String> response = productDetailsController.removeItem(null);
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
	}

	@Test
	public void testRemoveItem() {
		Product product = getTestProduct();
		when(productDetailsService.removeItem(product.getProdId(), filePath)).thenReturn("Removed");
		productDetailsController.removeItem(product.getProdId());
	}

	@Test
	public void testAddItemFailure() {
		Product product = getTestProduct();
		when(productDetailsService.addItem(product, filePath)).thenReturn("Product not added");
		ResponseEntity<String> response = productDetailsController.addItem(null);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCodeValue());
	}

	@Test
	public void testGetAllProductsSuccess() throws Exception {
		String uri = "/products";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void testGetProductBuIdSuccess() throws Exception {
		when(productDetailsService.getProductById("PROD-321", filePath)).thenReturn(new Product());
		ResponseEntity<Product> response = productDetailsController.getProductById("PROD-321");
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}

	@Test
	public void testGetProductBuIdFailure() throws Exception {
		when(productDetailsService.getProductById("PROD-321", filePath)).thenReturn(null);
		productDetailsController.getProductById(null);
	}

	@Test
	public void testSave() {
		Order order = new Order();
		order.setOrderId("OR-123");
		order.setOrderDate("2019-07-11");
		order.setProdId("PROD-123");
		order.setUserID("USR-123");
		when(orderService.save(order)).thenReturn("User Order Placed Successfully");
		ResponseEntity<String> response = productDetailsController.save(order);
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
	}

	private Product getTestProduct() {
		Product product = new Product();
		product.setProdId("112");
		product.setProdName("TV");
		product.setPrice("1000");
		return product;
	}

}
