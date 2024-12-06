package com.asm.bean;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
	@NoArgsConstructor
	@Entity
	@Table(name="movies")
public class movie {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String moviename;
	String vimage;
	String ximage;
	String bimage;
	String content;
//    @ManyToMany
//    @JoinTable(
//        name = "categorymovie", // Bảng trung gian
//                joinColumns = @JoinColumn(name = "movieid"), // Khóa ngoại cho bảng movies
//                inverseJoinColumns = @JoinColumn(name = "categoryid") // Khóa ngoại cho bảng category
//    )
//    private Set<category> categories; // Mối quan hệ với các Category
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	public String getVimage() {
		return vimage;
	}
	public void setVimage(String vimage) {
		this.vimage = vimage;
	}
	public String getXimage() {
		return ximage;
	}
	public void setXimage(String ximage) {
		this.ximage = ximage;
	}
	public String getBimage() {
		return bimage;
	}
	public void setBimage(String bimage) {
		this.bimage = bimage;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	
	public movie( String moviename, String vimage, String ximage, String bimage, String content) {
		super();
		this.moviename = moviename;
		this.vimage = vimage;
		this.ximage = ximage;
		this.bimage = bimage;
		this.content = content;
	}
	
	
}
