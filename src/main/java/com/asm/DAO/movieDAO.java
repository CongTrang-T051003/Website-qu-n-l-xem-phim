package com.asm.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.asm.bean.User;
import com.asm.bean.categorymovie;
import com.asm.bean.movie;

public interface movieDAO extends JpaRepository<movie, Integer>{
	
	@Query("SELECT m FROM movie m WHERE m.moviename = ?1")
	List<movie> findByMoviename(String moviename);
	
	@Modifying // Thêm chú thích này
    @Transactional // Thêm chú thích này
	 @Query("DELETE FROM movie m WHERE m.id = ?1")
	 void deleteByMovieId(int movieId);
}
