package com.asm.CartServiceImp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.asm.model.DB;
import com.asm.model.Item;
import com.asm.service.ShoppingCartService;

@SessionScope
@Service
public class ShoppingCartServiceImp implements ShoppingCartService {
	Map<Integer, Item> map = new HashMap<>();
	@Override
	public void add(Integer id, Item sp) {
		// TODO Auto-generated method stub
		if(map.get(id) != null) {
			this.update(id, sp.getQty()+1);
		}
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		map.get(id).setQty(1);
		map.remove(id);
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for(Map.Entry<Integer, Item> item : map.entrySet()) {
			DB.items.get(item.getKey()).setQty(1);
		}
		map.clear();

		
	}

	@Override
	public Collection<Item> getItems() {
		// TODO Auto-generated method stub
		return map.values();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int count = 0;
		Set<Integer> set = map.keySet();
		for(Integer i : set) {
			count++;
		}
		return count;
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		int totalCount = 0;
		Set<Integer> set = map.keySet();
		for(Integer i : set) {
			totalCount += map.get(i).getQty();
		}
		return totalCount;
	}

	@Override
	public double getAmout() {
		// TODO Auto-generated method stub
		double amount = 0;
		Set<Integer> set = map.keySet();
		for(Integer i : set) {
			amount += map.get(i).getQty() * map.get(i).getPrice(); 
		}
		return amount;
		 
	}

	@Override
	public void increaseQuantity(Integer id) {
		// TODO Auto-generated method stub
		Item item = map.get(id);
		if(item != null) {
			item.setQty(item.getQty()+1);
		}
		
	}

	@Override
	public void decreaseQuantity(Integer id) {
		// TODO Auto-generated method stub
		Item item = map.get(id);
		if(item != null && item.getQty() > 1) {
			item.setQty(item.getQty()-1);
		}else if(item != null && item.getQty() == 1) {
			map.remove(id);
		}
	}

	@Override
	public void update(Integer id, int qty) {
		// TODO Auto-generated method stub
		map.get(id).setQty(qty);
		
	}

}
