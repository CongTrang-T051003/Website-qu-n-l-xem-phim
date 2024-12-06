package com.asm.Controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asm.DAO.movieDAO;
import com.asm.DAO.userDAO;
import com.asm.bean.User;
import com.asm.bean.movie;

import ch.qos.logback.core.model.Model;

import org.mockito.*;

@ExtendWith(MockitoExtension.class)
class movieControllerTest {

    @Mock
    private movieDAO moviedao; // Đối tượng giả lập cho MovieDAO

    @InjectMocks
    private movieController moviecontroller; // Controller mà bạn đang kiểm thử

    @Mock
    private Model model; // Đối tượng giả lập cho Model

    @Mock
    private RedirectAttributes redirectAttributes; // Đối tượng giả lập cho RedirectAttributes

    private List<movie> movies; // Danh sách các đối tượng Movie

    @BeforeEach
    public void setUp() {
        movies = new ArrayList<>(); // Khởi tạo danh sách
        // Thêm các đối tượng Movie vào danh sách
        movies.add(new movie(1, "The Lion King", "lion_king_v.jpg", "lion_king_x.jpg", "lion_king_b.jpg", 
            "A young lion prince flees his kingdom after the death of his father, but returns as an adult to reclaim his throne."));
        movies.add(new movie(2, "Jurassic Park", "jurassic_park_v.jpg", "jurassic_park_x.jpg", "jurassic_park_b.jpg", 
            "During a preview tour, a theme park suffers a major power breakdown that allows its cloned dinosaur exhibits to run amok."));
        movies.add(new movie(3, "Finding Nemo", "finding_nemo_v.jpg", "finding_nemo_x.jpg", "finding_nemo_b.jpg", 
            "After his son is captured in the Great Barrier Reef and taken to Sydney, a timid clownfish sets out on a journey to bring him home."));
        movies.add(new movie(4, "Star Wars: Episode IV - A New Hope", "star_wars_v.jpg", "star_wars_x.jpg", "star_wars_b.jpg", 
            "In a distant galaxy, a young farm boy joins a group of rebels to fight against the evil Galactic Empire and rescue a princess."));
        movies.add(new movie(5, "Toy Story", "toy_story_v.jpg", "toy_story_x.jpg", "toy_story_b.jpg", 
            "A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boys room."));
    }

    @Test
    void tc017() {
        // Given
     // Gọi phương thức findAll
        when(moviedao.findAll()).thenReturn(movies); // Thiết lập hành vi giả lập
        List<movie> result = moviedao.findAll(); 
        List<movie> moviegetname = new ArrayList();
        moviegetname.add(result.get(0));
        String moviename = "The Lion King";
        when(moviedao.findByMoviename(moviename)).thenReturn(moviegetname); // Thiết lập hành vi giả lập
        // When
       

        // Then
        assertNotNull(result, "Movies should not be null");
        assertEquals(5, result.size(), "There should be 5 movies in the list");
        for (int i =0; i< moviegetname.size() ; i++) {
        	  assertEquals("The Lion King", moviedao.findByMoviename(moviename).get(i).getMoviename(), "First movie should be 'The Lion King'");
			
		}
      
        
    }
    @Test
    void tc019() {
        // Given
     // Gọi phương thức findAll
        when(moviedao.findAll()).thenReturn(movies); // Thiết lập hành vi giả lập
        List<movie> result = moviedao.findAll(); 
        List<movie> moviegetname = new ArrayList();
        moviegetname.add(result.get(0));
        String moviename = "The Lion King";
        when(moviedao.findByMoviename(moviename)).thenReturn(moviegetname); // Thiết lập hành vi giả lập
        // When
       

        // Then
        assertNotNull(result, "Movies should not be null");
        assertEquals(5, result.size(), "There should be 5 movies in the list");
        for (int i =0; i< moviegetname.size() ; i++) {
        	  assertEquals("The Lion King", moviedao.findByMoviename(moviename).get(i).getMoviename(), "First movie should be 'The Lion King'");
			
		}
      
        
    }
    
    @Test
    void tc49() {
        // Given
        movie newMovie = new movie(6, "Finding Nemo 2", "finding_nemo_v.jpg", "finding_nemo_x.jpg", "finding_nemo_b.jpg", 
            "A clownfish searches for his son.");
        
        // When
        movies.add(newMovie);
        

        // Gọi lại findAll để kiểm tra
        when(moviedao.findAll()).thenReturn(movies);
        
        // Then
        List<movie> result = moviedao.findAll();
        assertNotNull(result, "Movies should not be null");
        assertEquals(6, result.size(), "There should be 3 movies in the list now");
        assertEquals("Finding Nemo 2", result.get(5).getMoviename(), "Pass");
    }
    
    @Test
    void tc50() {
        // Given
        movie newMovie = new movie(6, "Finding Nemo 3", "finding_nemo_v.jpg", "finding_nemo_x.jpg", "finding_nemo_b.jpg", 
            "A clownfish searches for his son.");
        
        for(int i = 0 ; i<movies.size();i++) {
        	if(movies.get(i).getId() == 3 ) {
        		movies.get(i).setMoviename("Finding Nemo 3");
        	}
        }
        // When
        

        // Gọi lại findAll để kiểm tra
        when(moviedao.findAll()).thenReturn(movies);
        
        // Then
        List<movie> result = moviedao.findAll();
        assertNotNull(result, "Movies should not be null");
        assertEquals(5, result.size(), "There should be 3 movies in the list now");
        assertEquals("Finding Nemo 3", result.get(2).getMoviename(), "Pass");
    }
    
    @Test
    void tc51() {
        // Given
        movie newMovie = new movie(6, "Finding Nemo 3", "finding_nemo_v.jpg", "finding_nemo_x.jpg", "finding_nemo_b.jpg", 
            "A clownfish searches for his son.");
        
        for(int i = 0 ; i<movies.size();i++) {
        	if(movies.get(i).getId() == 3 ) {
        		movies.remove(i);
        	}
        }
        // When
        

        // Gọi lại findAll để kiểm tra
        when(moviedao.findAll()).thenReturn(movies);
        
        // Then
        List<movie> result = moviedao.findAll();
        assertNotNull(result, "Movies should not be null");
        assertEquals(4, result.size(), "There should be 3 movies in the list now");
    }
    
    @Test
    void tc55() {
        // Given
        movie newMovie = new movie(6, "Finding Nemo 3", "finding_nemo_v.jpg", "finding_nemo_x.jpg", "finding_nemo_b.jpg", 
            "");
        
  
        	if(newMovie.getContent().equals("") ) {
        		
        	}
        	else {
        		movies.add(newMovie);
        	}
        
        // When
        

        // Gọi lại findAll để kiểm tra
        when(moviedao.findAll()).thenReturn(movies);
        
        // Then
        List<movie> result = moviedao.findAll();
        assertNotNull(result, "Movies should not be null");
        assertEquals(5, result.size(), "There should be 3 movies in the list now");
    }
    
    @Test
    void tc56() {
        // Given
    	movie newMovie = new movie(6, "", "finding_nemo_v.jpg", "finding_nemo_x.jpg", "finding_nemo_b.jpg", 
                "A clownfish searches for his son.");
        
  
        	if(newMovie.getMoviename().equals("") ) {
        		
        	}
        	else {
        		movies.add(newMovie);
        	}
        
        // When
        

        // Gọi lại findAll để kiểm tra
        when(moviedao.findAll()).thenReturn(movies);
        
        // Then
        List<movie> result = moviedao.findAll();
        assertNotNull(result, "Movies should not be null");
        assertEquals(5, result.size(), "There should be 3 movies in the list now");
    }
    
    @Test
    void tc57() {
        // Given
    	movie newMovie = new movie(6, "Finding Nemo 3", "", "", "", 
                "A clownfish searches for his son.");
        
  
        	if(newMovie.getBimage().equals("")||newMovie.getVimage().equals("") ||newMovie.getXimage().equals("")  ) {
        		
        	}
        	else {
        		movies.add(newMovie);
        	}
        
        // When
        

        // Gọi lại findAll để kiểm tra
        when(moviedao.findAll()).thenReturn(movies);
        
        // Then
        List<movie> result = moviedao.findAll();
        assertNotNull(result, "Movies should not be null");
        assertEquals(5, result.size(), "There should be 3 movies in the list now");
    }
    
    @Test
    void tc61() {
        // Given
    	String conttent =  "";

    	for(int i = 0 ; i<movies.size();i++) {
        	if(movies.get(i).getId() == 3 ) {
        		if( conttent.equals("")  ) {
            		
            	}
            	else {
            		movies.get(i).setContent(conttent);
            	}
        	}
        }
  
        	
        
        // When
        

        // Gọi lại findAll để kiểm tra
        when(moviedao.findAll()).thenReturn(movies);
        
        // Then
        List<movie> result = moviedao.findAll();
        assertNotNull(result, "Movies should not be null");
        assertEquals(5, result.size(), "There should be 3 movies in the list now");
        assertNotEquals("", result.get(3).getContent(), "Pass");

    }
    
    @Test
    void tc62() {
        // Given
    	String moviename =  "";

    	for(int i = 0 ; i<movies.size();i++) {
        	if(movies.get(i).getId() == 3 ) {
        		if( moviename.equals("")  ) {
            		
            	}
            	else {
            		movies.get(i).setMoviename(moviename);
            	}
        	}
        }
  
        	
        
        // When
        

        // Gọi lại findAll để kiểm tra
        when(moviedao.findAll()).thenReturn(movies);
        
        // Then
        List<movie> result = moviedao.findAll();
        assertNotNull(result, "Movies should not be null");
        assertEquals(5, result.size(), "There should be 3 movies in the list now");
        assertNotEquals("", result.get(3).getMoviename(), "Pass");

    }
    
    @Test
    void tc63() {
        // Given
    	String bimage = "";
    	String vimage = "";
    	String ximage = "";
  
    	
    	for(int i = 0 ; i<movies.size();i++) {
        	if(movies.get(i).getId() == 3 ) {
        		if(bimage.equals("") ) {
            		
            	}
            	else {
            		movies.get(i).setBimage(bimage);
            	}
        		if(vimage.equals("") ) {
            		
            	}
            	else {
            		movies.get(i).setVimage(vimage);
            	}
        		if(ximage.equals("") ) {
            		
            	}
            	else {
            		movies.get(i).setXimage(ximage);
            	}
        		
        	}
        }
        	
        
        // When
        

        // Gọi lại findAll để kiểm tra
        when(moviedao.findAll()).thenReturn(movies);
        
        // Then
        List<movie> result = moviedao.findAll();
        assertNotNull(result, "Movies should not be null");
        assertEquals(5, result.size(), "There should be 3 movies in the list now");
        assertNotEquals("", result.get(3).getBimage(), "pass");
        assertNotEquals("", result.get(3).getVimage(), "pass");
        assertNotEquals("", result.get(3).getXimage(), "pass");
    }
    
    @Test
    void tc64() {
        // Given
    	int idmoive =  6;

    	for(int i = 0 ; i<movies.size();i++) {
        	if(movies.get(i).getId() == 3 ) {
        		if( movies.get(i).getId()==idmoive  ) {
            		movies.remove(i);
            	}
            
        	}
        }
  
        	
        
        // When
        

        // Gọi lại findAll để kiểm tra
        when(moviedao.findAll()).thenReturn(movies);
        
        // Then
        List<movie> result = moviedao.findAll();
        assertNotNull(result, "Movies should not be null");
        assertEquals(5, result.size(), "pass");

    }
    
    @Test
    void tc65() {
        // Given
    	movie movie = null;

    	for(int i = 0 ; i<movies.size();i++) {
        	if(movies.get(i).getId() == 1 ) {
        		movie = movies.get(i);
            	
            
        	}
        }
  
        	
        
        // When
        

        // Gọi lại findAll để kiểm tra
        when(moviedao.findAll()).thenReturn(movies);
        
        // Then
        List<movie> result = moviedao.findAll();
        assertNotNull(result, "Movies should not be null");
        assertEquals(5, result.size(), "pass");
        assertEquals("The Lion King", movie.getMoviename(), "pass");

    }
    }

    
//    @Test
//    void tesposthowmovie() throws Exception {
//        // given
//    	List<movie> movie =  new ArrayList<>(); 
//    	movie.add(new movie(1, "The Lion King", "lion_king_v.jpg", "lion_king_x.jpg", "lion_king_b.jpg", 
//    	            "A young lion prince flees his kingdom after the death of his father, but returns as an adult to reclaim his throne."));
//    	String moviename = "The Lion King";
//    	when(moviedao.findAll()).thenReturn(movies);
//    	when(moviedao.findByMoviename("The Lion King")).thenReturn(movie);
//        assertNotNull(moviedao.findAll(), "movies should not be null");
//        assertNotNull(moviedao.findByMoviename(moviename), "movies should not be null");
//    }
    
    

