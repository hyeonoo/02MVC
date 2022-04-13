package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.*;




public class UpdateProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {

		String prodNo=request.getParameter("prodNo");
		
		Product product = new Product();
		product.setProdNo(Integer.parseInt(prodNo));
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setManufDay(request.getParameter("manufDay"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setImgFile(request.getParameter("imgFile"));
		
		ProductService service = new ProductServiceImpl();
		service.updateProduct(product);
		
		
		
		
		return "redirect:/getProduct.do?prodNo="+prodNo;

	}
}