package com.model2.mvc.service.product.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.domain.Product;

public class ProductServiceImpl implements ProductService{
	
	private ProductDAO productDAO;
	
	public ProductServiceImpl() {
		System.out.println("ImplProductServiceDAO start");
		
		productDAO = new ProductDAO();
		
		System.out.println("ImplProductServiceDAO end");
	}
	
	public void addProduct(Product product) throws Exception{
		System.out.println("ImplAddProduct start");
		
		productDAO.insertProduct(product);
		
		System.out.println("ImplAddProduct and");
	}
	
	public Product getProduct(int prodNo) throws Exception{
		System.out.println("ImplVOGetProduct start");
		
		return productDAO.findProduct(prodNo);
		
		
	}
	
	public Map<String,Object> getProductList(Search search) throws Exception{
		System.out.println("ImplHashGetProductList start");
		
		return productDAO.getProductList(search);
	}
	
	public void updateProduct(Product product) throws Exception{
		System.out.println("ImplIpdateProduct start");
		
		productDAO.updateProduct(product);
		
		System.out.println("ImplIpdateProduct end");
	}

	
	

}
