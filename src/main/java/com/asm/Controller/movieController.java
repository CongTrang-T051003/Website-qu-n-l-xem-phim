package com.asm.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asm.DAO.categoryDAO;
import com.asm.DAO.categorymovieDAO;
import com.asm.DAO.movieDAO;
import com.asm.DAO.userDAO;
import com.asm.bean.User;
import com.asm.bean.category;
import com.asm.bean.categorymovie;
import com.asm.bean.movie;
import com.asm.ultils.ParamService;
import com.java5.slide5.dao.CategoryDAO;

@Controller
@SessionAttributes("currentuser")
@RequestMapping("management")
public class movieController {
	@Autowired
	ParamService paramService;
    @Autowired
    movieDAO moviedao;
    @Autowired
    categoryDAO categorydao;
    @Autowired
    categorymovieDAO categorymoviedao;
    @GetMapping("/movies")
    public String getshowmovie(Model model, Integer pageNo, RedirectAttributes redirectAttributes) {
        // Kiểm tra pageno, mặc định là 0 nếu không có giá trị
        if (pageNo == null) {
        	pageNo = 1;
        }
        Pageable pageable = PageRequest.of(pageNo-1, 5);
        model.addAttribute("totalPage",moviedao.findAll(pageable).getTotalPages());

        
        // Tạo Pageable với số trang và kích thước trang
        
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("movies", moviedao.findAll(pageable)); // Gọi phương thức findAll với Pageable
        return "/management/movie/list"; // Tên mẫu Thymeleaf
    }
    
    @PostMapping("/movies")
    public String postshowUsers(Model model, Integer pageNo, RedirectAttributes redirectAttributes, String searchtext) {
        // Kiểm tra pageno, mặc định là 0 nếu không có giá trị
        if (pageNo == null) {
        	pageNo = 1;
        }
        System.out.println(searchtext);
        model.addAttribute("searchtext",searchtext);
    	if(searchtext == null || searchtext.equals("")) {
            Pageable pageable = PageRequest.of(pageNo-1, 5);
            model.addAttribute("totalPage",moviedao.findAll(pageable).getTotalPages());

            
            // Tạo Pageable với số trang và kích thước trang
            
            model.addAttribute("currentPage",pageNo);
            model.addAttribute("movies", moviedao.findAll(pageable)); // Gọi phương thức findAll với Pageable
            return "/management/movie/list"; // Tên mẫu Thymeleaf
    	}
    	else {
    		   Pageable pageable = PageRequest.of(pageNo-1, 5);
               model.addAttribute("totalPage",1);
               // Tạo Pageable với số trang và kích thước trang
               model.addAttribute("currentPage",1);
               model.addAttribute("movies", moviedao.findByMoviename(searchtext)); // Gọi phương thức findAll với Pageable
               return "/management/movie/list"; // Tên mẫu Thymeleaf
    	}
    }
    	
    
    @GetMapping("/movies/edit")
    public String getUpdateuser(@ModelAttribute("user") movie movie,Model model, RedirectAttributes redirectAttributes,  Integer movieid) {
    	
    	
    	model.addAttribute("movieid",movieid);
    	movie = moviedao.getById(movieid);	
		model.addAttribute("movie",movie);
		model.addAttribute("categorymovie",categorymoviedao.findcategoryidbymovieid(movie.getId()));
		model.addAttribute("categories",categorydao.findAll());
        return "/management/movie/edit"; // Tên mẫu Thymeleaf
    }
    
   @PostMapping("/movies/edit")
    public String postUpdateuser(@ModelAttribute("user") movie movie,@RequestParam(name = "categorymovie", required = false) List<Integer> categorymovie, Model model, RedirectAttributes redirectAttributes,  Integer movieid) {
	   Optional<movie> optionalMovie = moviedao.findById(movieid);
	   movie  movie2 = optionalMovie.get();
	   	movie2.setBimage(movie.getBimage());
		movie2.setContent(movie.getContent());
		movie2.setMoviename(movie.getMoviename());
		movie2.setVimage(movie.getVimage());
		movie2.setXimage(movie.getXimage());
		model.addAttribute("movieid",movieid);
	 	model.addAttribute("categorymovie",categorymovie);
	 	System.out.println(categorymovie);
		categorymoviedao.deleteByMovieId(movieid);
	 	for(int i = 0; i< categorymovie.size(); i++) {
	 		categorymoviedao.save(new categorymovie(categorymovie.get(i),movieid));
	 		
	 	}
	 	
		moviedao.save(movie2);
		model.addAttribute("movie",movie);
       return "redirect:/management/movies"; // Tên mẫu Thymeleaf
		
    }
   
   @GetMapping("/movies/deleted")
   public String postDeletedMovie(@ModelAttribute("user") movie movie,@RequestParam(name = "categorymovie", required = false) List<Integer> categorymovie, Model model, RedirectAttributes redirectAttributes,  Integer movieid) {
		categorymoviedao.deleteByMovieId(movieid);
	   moviedao.deleteByMovieId(movieid);
      return "redirect:/management/movies"; // Tên mẫu Thymeleaf
		
   }
   
   @GetMapping("/movies/add")
   public String getAddMovie(@ModelAttribute("user") movie movie,Model model,@RequestParam(name = "categorymovie", required = false) List<Integer> categorymovie, RedirectAttributes redirectAttributes,  Integer movieid) {
   	
	   categorymovie = new ArrayList<Integer>();
   		movie = new movie("", "", "", "", "");
		model.addAttribute("movie",movie);
		model.addAttribute("categorymovie", categorymovie);
		model.addAttribute("categories",categorydao.findAll());
       return "/management/movie/add"; // Tên mẫu Thymeleaf
   }
   
   @PostMapping("/movies/add")
   public String postAddMovie(@ModelAttribute("user") movie movie,Model model,@RequestParam(name = "categorymovie", required = false) List<Integer> categorymovie, RedirectAttributes redirectAttributes,  Integer movieid) {
   	
		model.addAttribute("movie",movie);
		model.addAttribute("categorymovie", categorymovie);
		model.addAttribute("categories",categorydao.findAll());
		moviedao.save(movie);
		for(int i = 0; i< categorymovie.size(); i++) {
	 		categorymoviedao.save(new categorymovie(categorymovie.get(i),movie.getId()));
	 		
	 	}
       return "redirect:/management/movies";
   }

}