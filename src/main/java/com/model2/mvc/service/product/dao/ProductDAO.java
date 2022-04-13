package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.*;


public class ProductDAO {
	
	public ProductDAO() {
	}

	public void insertProduct(Product product) throws Exception{
		
		System.out.println("insertproductDAO start");
		
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO PRODUCT VALUES (seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, product.getProdName());
		stmt.setString(2, product.getProdDetail());
		stmt.setString(3, product.getManufDay().replace("-", ""));
		stmt.setInt(4, product.getPrice());
		stmt.setString(5, product.getImgFile());
	
		stmt.executeUpdate();
		
		System.out.println(sql);
		
		con.close();
		
		System.out.println("insertproductDAO end");
	}
	
	public Product findProduct(int prodNo) throws Exception {
		
		System.out.println("findProduct start");
		
		Connection con = DBUtil.getConnection();

		String sql = "SELECT * FROM PRODUCT WHERE PROD_NO=? ";
		System.out.println(sql);
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);
		
		System.out.println(prodNo);
		
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs);

		Product product = null;
		while (rs.next()) {
			product = new Product();
			product.setProdNo(rs.getInt("PROD_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setProdDetail(rs.getString("PROD_DETAIL"));
			product.setManufDay(rs.getString("MANUFACTURE_DAY"));
			product.setPrice(rs.getInt("PRICE"));
			product.setImgFile(rs.getString("IMAGE_FILE"));
			product.setRegDate(rs.getDate("REG_DATE"));
		}
		
		System.out.println(sql);
		rs.close();
		stmt.close();
		con.close();
		
		System.out.println("findProduct end");
		return product;
		
	}

	public Map<String, Object> getProductList(Search search) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object> ();

		System.out.println("getP.DAOMap start");
		
		Connection con = DBUtil.getConnection();
		System.out.println(search.getSearchCondition()+search.getSearchKeyword());

		String sql = "SELECT PROD_NO, PROD_NAME, Price, REG_DATE FROM product ";
		if (search.getSearchCondition() != null) {
			if (search.getSearchCondition().equals("0") && !search.getSearchKeyword().equals("")) {
				sql += " where PROD_NO LIKE '%"  + search.getSearchKeyword() + "%'";
			} else if (search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
				sql += " where PROD_NAME LIKE '%" + search.getSearchKeyword() + "%'";
			} else if (search.getSearchCondition().equals("2") && !search.getSearchKeyword().equals("")) {
				sql += " where Price LIKE '%" + search.getSearchKeyword() + "%'";
			}
		}
		sql += " ORDER BY PROD_NO";
		
		System.out.println("ProductDAO :: Original SQL :: " + sql);
		
		int totalCount = this.getTotalCount(sql);
		System.out.println("ProductDAO :: totalCount :: "+ totalCount);
		
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		System.out.println(search);
		
		List<Product> list = new ArrayList<Product>();
		
		while(rs.next()){
			Product product = new Product();
			product.setProdNo(rs.getInt("PROD_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setPrice(rs.getInt("Price"));
			System.out.println(rs.getInt("Price"));
			product.setRegDate(rs.getDate("REG_DATE"));
			
			list.add(product);
		}
		
		//==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage 의 게시물 정보 갖는 List 저장
		map.put("list", list);
		
		rs.close();
		stmt.close();
		con.close();
	
		return map;

	}
	
	private int getTotalCount(String sql) throws Exception {
		// TODO Auto-generated method stub
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	private String makeCurrentPageSql(String sql, Search search) {
		// TODO Auto-generated method stub
		sql = 	"SELECT * "+ 
				"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
								" 	FROM (	"+sql+" ) inner_table "+
								"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
				"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
	
		System.out.println("ProductDAO :: make SQL :: "+ sql);	
	
		return sql;
	}

	public void updateProduct(Product product) throws Exception {
		
		System.out.println("updateProduct start");
		Connection con = DBUtil.getConnection();

		String sql = "UPDATE PRODUCT SET PROD_NAME=?, PROD_DETAIL=?, MANUFACTURE_DAY=?, PRICE=?, IMAGE_FILE=? where PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, product.getProdName());
		stmt.setString(2, product.getProdDetail());
		stmt.setString(3, product.getManufDay());
		stmt.setInt(4, product.getPrice());
		stmt.setString(5, product.getImgFile());
		
		stmt.setInt(6, product.getProdNo());
		
		
		stmt.executeUpdate();
		
		System.out.println("updateProduct end");
		con.close();
	}
	
	
}
