package com.cts.util;

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
		/*
		 * PowerMockito.mockStatic(Files.newInputStream.class);
		 * PowerMockito.mock(Files.newInputStream(Mockito.any())).thenThrow(IOException.
		 * class); boolean excOccured =false; String a; try { a =
		 * productExcelFile.addItemInExcel(fileName, pro); }catch (IOException ioe) {
		 * excOccured =true; } Assert.assertTrue(excOccured); assertNotNull(a);
		 * Assert.assertEquals("", a);
		 */

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

}
