package com.asm.service;

import java.util.Collection;

import com.asm.model.Item;

public interface ShoppingCartService {
	void add(Integer id, Item sp);
	void remove(Integer id);
	void clear();
	Collection<Item> getItems();
	int getCount();
	public int getTotalCount();
	double getAmout();
	void increaseQuantity(Integer id);
	void decreaseQuantity(Integer id);
	void update(Integer id, int qty);
}
