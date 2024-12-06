package com.asm.Controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asm.DAO.userDAO;
import com.asm.bean.User;
import com.asm.ultils.ParamService;

@Controller
@SessionAttributes("currentuser")
@RequestMapping("management")
public class userController {
	@Autowired
	ParamService paramService;
    @Autowired
    userDAO userdao;
    @RequestMapping("/users")
    public String showUsers(Model model, Integer pageNo, RedirectAttributes redirectAttributes) {
        // Kiểm tra pageno, mặc định là 0 nếu không có giá trị
        if (pageNo == null) {
        	pageNo = 1;
        }
        Pageable pageable = PageRequest.of(pageNo-1, 5);
 

   
        model.addAttribute("totalPage",userdao.findAll(pageable).getTotalPages());

        
        // Tạo Pageable với số trang và kích thước trang
        
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("users", userdao.findAll(pageable)); // Gọi phương thức findAll với Pageable
        return "/management/users/list"; // Tên mẫu Thymeleaf
    }
    
    @GetMapping("/users/add")
    public String getAddUser(@ModelAttribute("user") User user,Model model, RedirectAttributes redirectAttributes) {
    	
    	String username = paramService.getString("username", "");
		String password = paramService.getString("password", "");
		String email = paramService.getString("email", "");
		String role = paramService.getString("role", "");
		
		user = new User(username, password, email, role);
		model.addAttribute("user",user);
        return "/management/users/add"; // Tên mẫu Thymeleaf
    }
    
    @PostMapping("/users/add")
    public String postAddUser(@Validated @ModelAttribute("user") User user ,Model model, RedirectAttributes redirectAttributes) {
    	
    	userdao.save(user);

    	model.addAttribute("user",user);
        return "redirect:/management/users";
    }
    
    @RequestMapping("users/deleted")
	public String deleted(Model model, @ModelAttribute("user") User user, Integer userid) {
		model.addAttribute("repeatpass","");
		model.addAttribute("userid",userid);
		userdao.deleteById(userid);
		return "redirect:/management/users";
	}
    
    @GetMapping("/users/edit")
    public String getUpdateuser(@ModelAttribute("user") User user,Model model, RedirectAttributes redirectAttributes,  Integer userid) {
    	
    	model.addAttribute("userid",userid);
    	user = userdao.getById(userid);
		model.addAttribute("user",user);
        return "/management/users/edit"; // Tên mẫu Thymeleaf
    }
    
    @PostMapping("/users/edit")
    public String postUpdateuser(@ModelAttribute("user") User user,Model model, RedirectAttributes redirectAttributes,  Integer userid) {
    	Optional<User> optionalUser = userdao.findById(userid);
    	User user2 = optionalUser.get();
    	user2.setEmail(user.getEmail());
    	user2.setPassword(user.getPassword());
    	user2.setRole(user.getRole());
    	user2.setUsername(user.getUsername());
    	model.addAttribute("userid",userid);
    	userdao.save(user2);
		model.addAttribute("user",user);
        return "redirect:/management/users"; // Tên mẫu Thymeleaf
    }
}