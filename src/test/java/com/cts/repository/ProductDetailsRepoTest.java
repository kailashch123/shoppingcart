package com.cts.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.cts.controller.AbstractTest;
import com.cts.model.Product;
import com.cts.repository.impl.ProductDetailssRepo;
import com.cts.util.ProductExcelFile;
import com.cts.util.RWExcelProduct;

public class ProductDetailsRepoTest extends AbstractTest {

	@InjectMocks
	ProductDetailssRepo productDetailssRepo;

	@Mock
	ProductExcelFile productExcelFile;
	
	@Mock
	RWExcelProduct rwExcelProduct;
	
	@Before
	@Override
	public void setUp() {
		super.setUp();
	}
	@Test
	public void testProductAdd() {
		String fileName = "./src/main/resources/excel/product.xlsx";
		Product product = new Product();
		product.setProdId("102");
		product.setProdName("nokia");
		product.setPrice("999");
		when(productExcelFile.addItemInExcel(fileName, product)).thenReturn("Added");
		String res = productDetailssRepo.addItem(product);
		assertEquals(res, "Added");
	}

	@Test
	public void testProductRemove() {
		String fileName = "./src/main/resources/excel/product.xlsx";
		Product product = new Product();
		product.setProdId("102");
		product.setProdName("nokia");
		product.setPrice("999");
		when(productExcelFile.removeItemFromExcel(fileName,product.getProdId() )).thenReturn("Removed");
		String res = productDetailssRepo.removeItem(product.getProdId());
		assertEquals(res, "Removed");
	}
	
	@Test
	public void testGetProductById() {
		Product product = new Product();
		product.setProdId("102");
		product.setProdName("nokia");
		product.setPrice("999");
		when(rwExcelProduct.getProductById(product.getProdId() )).thenReturn(product);
		Product responseProduct = productDetailssRepo.getProductById(product.getProdId());
		assertEquals(product.getProdId(), responseProduct.getProdId());
	}
	
	@Test
	public void testGetAllProducts() {
		Product product1 = new Product();
		product1.setProdId("102");
		product1.setProdName("nokia");
		product1.setPrice("999");
		
		Product product2 = new Product();
		product2.setProdId("103");
		product2.setProdName("samsung");
		product2.setPrice("1000");
		
		List<Product> pList = new ArrayList<Product>();
		pList.add(product1);
		pList.add(product2);
		
		when(rwExcelProduct.getAllProducts()).thenReturn(pList);
		List<Product> responsePlist = productDetailssRepo.getAllProducts();
		assertEquals(2, responsePlist.size());
	}
}
