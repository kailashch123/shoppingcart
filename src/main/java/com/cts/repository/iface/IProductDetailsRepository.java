package com.cts.repository.iface;

import java.util.List;

import com.cts.model.Product;

public interface IProductDetailsRepository {

	String addItem(final Product pro);

	String removeItem(String prodId);

	List<Product> getAllProducts();

	Product getProductById(String prodId);
}
