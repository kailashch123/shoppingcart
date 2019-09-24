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
	
	@Test
	public void addItemInExcelTest() {
		Product pro = new Product();
		pro.setPrice("345.66");
		pro.setProdId("23234");
		pro.setProdName("Mobile");
		rWExcelProduct.addItemInExcel(pro);
		String expected = "Item added Successfully";
		String actual = rWExcelProduct.addItemInExcel(pro);
		assertEquals(expected, actual);
	}
	
	@Test
	public void removeItemFromExcelTest() {
		String filePath = "./src/main/resources/excel/product.xlsx";
		int id = 12;
		rWExcelProduct.removeItemFromExcel(filePath, id);
	}
	
	@Test
	public void readExcelTest() {
		List<Product> actual = rWExcelProduct.readExcel();
		assertNotNull(actual);
	}
	
	@Test
	public void getAllProductsTest() {
		assertNotNull(rWExcelProduct.getAllProducts().size());
	}
	
	@Test
	public void getProductByIdTest() {
		Product pro = new Product();
		pro.setPrice("345.66");
		pro.setProdId("23234");
		pro.setProdName("Mobile");
		assertEquals("Mobile", rWExcelProduct.getProductById("23234").getProdName());
	}

}
