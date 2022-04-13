package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

public class AddProductAction extends Action {

	@Override
	public String execute( HttpServletRequest request, HttpServletResponse response) throws Exception{
			
		System.out.println("AddProductAction start");
		
		Product product = new Product();
		product.setProdName(request.getParameter("prodName"));
		System.out.println(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setManufDay(request.getParameter("manufDay"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setImgFile(request.getParameter("imgFile"));
		
		ProductService service = new ProductServiceImpl();
		service.addProduct(product);
		request.setAttribute("product", product);
		
		System.out.println(product);
		System.out.println("AddProductAction end");
		
		return "forward:/product/addProduct.jsp";
	}
}
