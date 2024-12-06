package com.asm.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.asm.bean.User;
import com.asm.bean.categorymovie;

public interface categorymovieDAO extends JpaRepository<categorymovie, Integer> {
	
	@Query("SELECT c.categoryid FROM categorymovie c WHERE c.movieid = ?1")
	List<Integer> findcategoryidbymovieid(int id);
	@Modifying // Thêm chú thích này
    @Transactional // Thêm chú thích này
	 @Query("DELETE FROM categorymovie c WHERE c.movieid = ?1")
	 void deleteByMovieId(int movieId);
}
