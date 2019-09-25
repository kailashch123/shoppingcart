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
import com.cts.repository.impl.ProductDetailsRepoImpl;
import com.cts.util.ProductExcelFile;
import com.cts.util.RWExcelProduct;

public class ProductDetailsRepoTest extends AbstractTest {
	
	private String filePath = "./src/main/resources/excel/product.xlsx";

	@InjectMocks
	ProductDetailsRepoImpl productDetailsRepository;

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
		Product product = getDummyProduct();
		when(productExcelFile.addItemInExcel(filePath, product)).thenReturn("Added");
		String res = productDetailsRepository.addItem(product, filePath);
		assertEquals(res, "Added");
	}

	@Test
	public void testProductRemove() {
		Product product = new Product();
		product.setProdId("102");
		product.setProdName("nokia");
		product.setPrice("999");
		when(productExcelFile.removeItemFromExcel(filePath,product.getProdId() )).thenReturn("Removed");
		String res = productDetailsRepository.removeItem(product.getProdId(), filePath);
		assertEquals(res, "Removed");
	}
	
	@Test
	public void testGetProductById() {
		Product product = new Product();
		product.setProdId("102");
		product.setProdName("nokia");
		product.setPrice("999");
		when(rwExcelProduct.getProductById(product.getProdId(), filePath)).thenReturn(product);
		Product responseProduct = productDetailsRepository.getProductById(product.getProdId(), filePath);
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
		
		when(rwExcelProduct.getAllProducts(filePath)).thenReturn(pList);
		List<Product> responsePlist = productDetailsRepository.getAllProducts(filePath);
		assertEquals(2, responsePlist.size());
	}
	
	private Product getDummyProduct() {
		Product product = new Product();
		product.setProdId("102");
		product.setProdName("nokia");
		product.setPrice("999");
		return product;
	}
}
