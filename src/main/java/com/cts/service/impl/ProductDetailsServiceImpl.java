/**
 * This class is used to give the product details.
 * 
 * @author 764432
 *
 */
package com.cts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Product;
import com.cts.repository.iface.IProductDetailsRepository;
import com.cts.service.iface.IProductDetailsService;

@Service("productDetailsService")
public class ProductDetailsServiceImpl implements IProductDetailsService {

	@Autowired
	IProductDetailsRepository productDetailsRepository;

	/**
	 * It is used to add a product item into excel.
	 * 
	 * @param pro
	 * @return
	 */

	@Override
	public String addItem(Product pro) {
		return productDetailsRepository.addItem(pro);
	}

	/**
	 * It is used to remove a product item from excel.
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public String removeItem(String id) {
		return productDetailsRepository.removeItem(id);
	}

	/**
	 * It is used to give all product details.
	 * 
	 * @return
	 */
	@Override
	public List<Product> getAllProducts() {
		return productDetailsRepository.getAllProducts();
	}

	/**
	 * It is used to get the product details by Id.
	 * 
	 * @param productId
	 * @return
	 */
	@Override
	public Product getProductById(String productId) {
		return productDetailsRepository.getProductById(productId);
	}
}
