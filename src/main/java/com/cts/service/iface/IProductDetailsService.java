package com.cts.service.iface;

import java.util.List;

import com.cts.model.Product;

public interface IProductDetailsService {

	String addItem(Product pro, String filePath);

	String removeItem(String id, String filePath);

	List<Product> getAllProducts(String filePath);

	Product getProductById(String productId, String filePath);

}
