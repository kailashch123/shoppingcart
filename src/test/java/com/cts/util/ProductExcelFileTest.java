package com.cts.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.model.Product;

@RunWith(SpringRunner.class)
public class ProductExcelFileTest {

	@InjectMocks
	ProductExcelFile productExcelFile;

	@Test
	public void addItemInExcelTest() {
		String fileName = "./src/main/resources/excel/product.xlsx";
		Product pro = new Product();
		pro.setPrice("89.56");
		pro.setProdId("12");
		pro.setProdName("CarBox");
		String addItemResponse = productExcelFile.addItemInExcel(fileName, pro);
		assertNotNull(addItemResponse);
	}
	
	@Test
	public void addItemInExcelFailureTest() {
		String fileName = "./src/main/resources/excel/nofile.xlsx";
		Product pro = new Product();
		String addItemResponse = productExcelFile.addItemInExcel(fileName, pro);
		String expected = "NoSuchFileException";
		assertEquals(expected, addItemResponse);
	}

	@Test
	public void removeItemFromExcelTest() {
		String fileName = "./src/main/resources/excel/product.xlsx";
		Product pro = new Product();
		pro.setPrice("89.56");
		pro.setProdId("12");
		pro.setProdName("CarBox");
		String removeItemResponse = productExcelFile.removeItemFromExcel(fileName, "12356");
		assertNull(removeItemResponse);
	}
	
	@Test
	public void removeItemFromExcelFailureTest() {
		String fileName = "./src/main/resources/excel/nofile.xlsx";
		String removeItemResponse = productExcelFile.removeItemFromExcel(fileName, "12356");
		String expected = "NoSuchFileException";
		assertEquals(expected, removeItemResponse);
	}

}
