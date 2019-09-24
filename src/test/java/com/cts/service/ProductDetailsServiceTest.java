package com.cts.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cts.model.Product;
import com.cts.repository.impl.ProductDetailsRepoImpl;
import com.cts.service.impl.ProductDetailsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductDetailsServiceTest {
	
	@InjectMocks
	ProductDetailsServiceImpl productDetailsService;
	
	@Mock
	ProductDetailsRepoImpl productDetailsRepository;
	

	@Test
	public void addItemTest(){
		Product pro = new Product();
		pro.setPrice("234.56");
		pro.setProdId("B345");
		pro.setProdName("Bottle");
		when(productDetailsRepository.addItem(pro)).thenReturn("Item Added Successfully");
		assertEquals("Item Added Successfully", productDetailsService.addItem(pro));
	}
	
	@Test
	public void removeItemTest(){
		when(productDetailsRepository.removeItem("B345")).thenReturn("Item deleted Successfully");
		assertEquals("Item deleted Successfully", productDetailsService.removeItem("B345"));
	}
	
	@Test
	public void getAllProductsTest(){
		List<Product> products = new ArrayList<>();
		Product p = new Product();
		p.setProdId("G23423");
		Product p1 = new Product();
		p1.setProdId("Y23423");
		products.add(p1);
		products.add(p);
		when(productDetailsRepository.getAllProducts()).thenReturn(products);
		assertNotNull(productDetailsService.getAllProducts());
	}
	
	@Test
	public void getProductByIdTest(){
		Product p = new Product();
		p.setProdId("G23423");
		when(productDetailsRepository.getProductById("G23423")).thenReturn(p);
		assertEquals(p.getProdId(), productDetailsService.getProductById("G23423").getProdId());
		assertEquals(p.getPrice() , productDetailsService.getProductById("G23423").getPrice());
		assertEquals(p.getProdName(), productDetailsService.getProductById("G23423").getProdName());
		
	}
}
