package com.model2.mvc.service.domain;

import java.sql.Date;


public class Product {
	
	private int prodNo;
	private String prodName;
	private String prodDetail;
	private String manufDay;
	private int price;
	private String imgFile;
	private Date regDate;
	
	public Product() {
	}
	
	public int getProdNo() {
		return prodNo;
	}


	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}


	public String getProdName() {
		return prodName;
	}


	public void setProdName(String prodName) {
		this.prodName = prodName;
	}


	public String getProdDetail() {
		return prodDetail;
	}


	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}


	public String getManufDay() {
		return manufDay;
	}


	public void setManufDay(String manufDay) {
		this.manufDay = manufDay;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getImgFile() {
		return imgFile;
	}


	public void setImgFile(String imgFile) {
		this.imgFile = imgFile;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	// Override
	public String toString() {
		return "ProductVO : [prodNo]" + prodNo
				+ "[prodName]" + prodName+ "[prodDetail]" + prodDetail + "[manufDay]" + manufDay
				+ "[price]" + price + "[imgFile]" + imgFile +" [regDate] "+regDate;
	}	
}