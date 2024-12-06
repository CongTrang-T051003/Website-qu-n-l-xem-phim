package com.asm.model;

import java.util.HashMap;
import java.util.Map;

import com.asm.bean.User;

public class DBuser {
	public static Map<Integer, User> users = new HashMap<>();
	static {
		users.put(1, new User("Admin1", "oldmovie24", "", "Admin"));
		users.put(2, new User("Manager1", "oldmovie24", "", "Manager"));
		users.put(3, new User("Uservip1", "oldmovie24", "", "Vip"));
		users.put(4, new User("User1", "oldmovie24", "", "Guest"));
		users.put(5, new User("User2", "oldmovie24", "", "Guest"));
		users.put(6, new User("Admin2", "oldmovie24", "", "Admin"));
		users.put(7, new User("Manager2", "oldmovie24", "", "Manager"));
		users.put(8, new User("Uservip2", "oldmovie24", "", "Vip"));
		users.put(9, new User("User3", "oldmovie24", "", "Guest"));
		users.put(10, new User("User4", "oldmovie24", "", "Guest"));
		users.put(11, new User("User5", "oldmovie24", "", "Guest"));
		users.put(12, new User("Admin3", "oldmovie24", "", "Admin"));
		users.put(13, new User("Manager3", "oldmovie24", "", "Manager"));
		users.put(14, new User("Uservip3", "oldmovie24", "", "Vip"));
		users.put(15, new User("User6", "oldmovie24", "", "Guest"));
	}
}
