package com.cts.service.iface;

import java.util.List;

import com.cts.model.Product;

public interface IProductDetailsService {

	String addItem(Product pro);

	String removeItem(String id);

	List<Product> getAllProducts();

	Product getProductById(String productId);

}
