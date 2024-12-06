package com.asm.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.bean.category;
import com.asm.bean.categorymovie;

public interface categoryDAO extends JpaRepository<category, Integer>{

}
