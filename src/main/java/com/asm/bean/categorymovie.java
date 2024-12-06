package com.asm.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
	@NoArgsConstructor
	@Entity
	@Table(name="categorymovie")
public class categorymovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính duy nhất (có thể là tự động tăng)
	Integer categoryid;
	Integer movieid;
	
//	 @ManyToOne
//	    @JoinColumn(name = "id") // Khóa ngoại liên kết đến category
//	    private category category; // Thể loại liên kết
//
//	    @ManyToOne
//	    @JoinColumn(name = "id") // Khóa ngoại liên kết đến movie
//	    private movie movie; // Bộ phim liên kết
	
	public Integer getCategoryid() {
		return categoryid;
	}



	public void setIdcategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}



	public Integer getMovieid() {
		return movieid;
	}



	public void setMovieid(Integer movieid) {
		this.movieid = movieid;
	}



	public categorymovie(Integer categoryid, Integer movieid) {
		super();
		this.categoryid = categoryid;
		this.movieid = movieid;
	}




	
	

	
	
	
}
