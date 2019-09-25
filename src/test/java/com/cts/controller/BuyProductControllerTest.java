package com.cts.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.model.Product;
import com.cts.model.User;
import com.cts.service.impl.AuthServiceImpl;
import com.cts.service.impl.ProductDetailsServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BuyProductController.class)
public class BuyProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuthServiceImpl authService;

	@MockBean
	private ProductDetailsServiceImpl productDetailsService;
	private String filePath = "./src/main/resources/excel/product.xlsx";
	
	@Test
	public void loginTest() throws Exception {
		String exampleCourseJson = "{\"password\":\"password\",\"userId\":\"user101\"}";
		User user = new User();
		user.setUserId("user101");
		user.setPassword("password");

		Mockito.when(authService.login(user)).thenReturn("User Looged in sucessfully");
		
		Product product = new Product();
		product.setProdId("101");
		product.setProdName("prodName");
		product.setPrice("999");
		Mockito.when(productDetailsService.addItem(product, filePath)).thenReturn("Product Added Successfully,Product Id:  101");
		
		Mockito.when(productDetailsService.getProductById(product.getProdId(), filePath)).thenReturn(product);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/placeorder").accept(MediaType.APPLICATION_JSON)
				.content(exampleCourseJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "User Order Placed Successfully!!!The user with ID => user101 has placed the Order with ID =>od-856429 having the prodcut with ID => 102";
		assertEquals(expected, result.getResponse().getContentAsString());
	}

	@Test
	public void loginTestElse() throws Exception {
		String exampleCourseJson = "{\"password\":\"password\",\"userId\":\"user12501\"}";

		Mockito.when(authService.login(Mockito.anyObject())).thenReturn("invalid user details with user Id:user12501");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/placeorder").accept(MediaType.APPLICATION_JSON)
				.content(exampleCourseJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "invalid user details with user Id:user12501";
		assertEquals(expected, result.getResponse().getContentAsString());
	}

}
