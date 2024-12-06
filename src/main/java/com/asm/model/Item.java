package com.asm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class Item {
	Integer id;
	String name;
	double price;
	int qty = 1;
	String img;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	

	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Item(Integer id, String name, double price, int qty, String img) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.img = img;
	}
	public Item() {
		super();
	}
	
	
}
