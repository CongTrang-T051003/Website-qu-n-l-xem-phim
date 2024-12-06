package com.asm.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.bean.User;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public interface userDAO extends JpaRepository<User, Integer>{

//	List<User> findAll(Pageable pageable);
	

	
	@Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);
	
}
