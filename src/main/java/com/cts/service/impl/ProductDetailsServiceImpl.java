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
	public String addItem(Product pro, String filePath) {
		return productDetailsRepository.addItem(pro, filePath);
	}

	/**
	 * It is used to remove a product item from excel.
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public String removeItem(String id, String filePath) {
		return productDetailsRepository.removeItem(id, filePath);
	}

	/**
	 * It is used to give all product details.
	 * 
	 * @return
	 */
	@Override
	public List<Product> getAllProducts(String filePath) {
		return productDetailsRepository.getAllProducts(filePath);
	}

	/**
	 * It is used to get the product details by Id.
	 * 
	 * @param productId
	 * @return
	 */
	@Override
	public Product getProductById(String productId, String filePath) {
		return productDetailsRepository.getProductById(productId, filePath);
	}
}
