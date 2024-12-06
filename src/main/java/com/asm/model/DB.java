package com.asm.model;

import java.util.HashMap;
import java.util.Map;

public class DB {
	public static Map<Integer, Item> items = new HashMap<>();
	static {
		items.put(1, new Item(1, "Kong vs Godzilla: New Empire", 100, 1, "kvgne.jpg"));
		items.put(2, new Item(2, "Spider-Man: No Way Home", 150, 1,"smnwh.jpg"));
		items.put(3, new Item(3, "The Batman", 120, 1,"tbm.jpg"));
		items.put(4, new Item(4, "Jurassic World: Dominion", 130, 1,"jwd.jpg"));
		items.put(5, new Item(5, "Dune", 200, 1,"d.jpg"));
		items.put(6, new Item(7, "Black Panther: Wakanda Forever", 170, 1,"bbwf.jpg"));


	}
}
