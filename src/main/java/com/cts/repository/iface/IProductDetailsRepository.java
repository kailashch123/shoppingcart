package com.cts.repository.iface;

import java.util.List;

import com.cts.model.Product;

public interface IProductDetailsRepository {

	String addItem(final Product pro, String filePath);

	String removeItem(String prodId, String filePath);

	List<Product> getAllProducts(String filePath);

	Product getProductById(String prodId, String filePath);
}
