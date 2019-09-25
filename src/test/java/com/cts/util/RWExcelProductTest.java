package com.cts.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.model.Product;

@RunWith(SpringRunner.class)
public class RWExcelProductTest {
	
	@InjectMocks
	RWExcelProduct rWExcelProduct;
	private String filePath = "./src/main/resources/excel/product.xlsx";
	
	@Test
	public void addItemInExcelTest() {
		Product product = getTestProduct();
		rWExcelProduct.addItemInExcel(product, filePath);
		String expected = "Item added Successfully";
		String actual = rWExcelProduct.addItemInExcel(product, filePath);
		assertEquals(expected, actual);
	}
	
	@Test
	public void removeItemFromExcelTest() {
		Product product = getTestProduct();
		rWExcelProduct.addItemInExcel(product, filePath);
		String expected = "Item added Successfully";
		String actual = rWExcelProduct.addItemInExcel(product, filePath);
		assertEquals(expected, actual);
		int prodId = Integer.valueOf(product.getProdId());
		rWExcelProduct.removeItemFromExcel(filePath, prodId);
	}
	
	@Test
	public void readExcelTest() {
		List<Product> actual = rWExcelProduct.readExcel(filePath);
		assertNotNull(actual);
	}
	
	@Test
	public void getAllProductsTest() {
		assertNotNull(rWExcelProduct.getAllProducts(filePath).size());
	}
	
	@Test
	public void getProductByIdTest() {
		Product p = getTestProduct();
		rWExcelProduct.addItemInExcel(p, filePath);
		String expected = "Item added Successfully";
		String actual = rWExcelProduct.addItemInExcel(getTestProduct(), filePath);
		assertEquals(expected, actual);
		
		Product product = rWExcelProduct.getProductById(p.getProdId(), filePath);
		assertEquals("Mobile", product.getProdName());
		
		String filePath = "./src/main/resources/excel/product.xlsx";
		int id = Integer.valueOf(p.getProdId());
		rWExcelProduct.removeItemFromExcel(filePath, id);
	}
	
	private Product getTestProduct() {
		Product product = new Product();
		product.setPrice("345.66");
		product.setProdId("23234");
		product.setProdName("Mobile");
		return product;
	}

}
