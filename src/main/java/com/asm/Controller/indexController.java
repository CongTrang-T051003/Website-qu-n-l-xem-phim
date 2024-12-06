package com.asm.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asm.DAO.categoryDAO;
import com.asm.DAO.categorymovieDAO;
import com.asm.DAO.movieDAO;
import com.asm.bean.User;
import com.asm.bean.movie;
import com.asm.ultils.ParamService;

@Controller
@SessionAttributes("currentuser")
@RequestMapping("index")
public class indexController {
	@Autowired
	ParamService paramService;
    @Autowired
    movieDAO moviedao;
    @Autowired
    categoryDAO categorydao;
    @Autowired
    categorymovieDAO categorymoviedao;
    @GetMapping("/trangchu")
    public String gettrangchu(@ModelAttribute("user")  User user, Model model, Integer pageNo, RedirectAttributes redirectAttributes) {
    	 model.addAttribute("movies", moviedao.findAll()); 
        return "/layout/index"; // Tên mẫu Thymeleaf
    }
    
    @GetMapping("/detail")
    public String getUpdateuser(@ModelAttribute("user") movie movie,@RequestParam(name = "categorymovie", required = false) List<Integer> categorymovie, Model model, RedirectAttributes redirectAttributes,  Integer movieid) {
    	model.addAttribute("movieid",movieid);
    	movie = moviedao.getById(movieid);	
		model.addAttribute("movie",movie);
		model.addAttribute("categorymovie",categorymoviedao.findcategoryidbymovieid(movie.getId()));
		model.addAttribute("categories",categorydao.findAll());
    	
    
        return "/layout/detail"; // Tên mẫu Thymeleaf
    }
}
