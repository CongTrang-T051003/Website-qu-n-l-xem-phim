package com.asm.bean;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
	@NoArgsConstructor
	@Entity
	@Table(name="category")

public class category {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String categoryname;
//	
//    @ManyToMany(mappedBy = "category") // mappedBy trỏ đến thuộc tính trong Movie
//    private Set<movie> movies; // Mối quan hệ với Movie
	  	
}
