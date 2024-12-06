package com.asm.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.websocket.Session;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asm.DAO.userDAO;
import com.asm.bean.User;
import com.asm.excel.write;
import com.asm.ultils.CookieService;
import com.asm.ultils.ParamService;
import com.asm.ultils.SessionService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("account")
public class AccountController {
	@Autowired
	CookieService cookieService;
	@Autowired
	ParamService paramService;
	@Autowired
	SessionService sessionService;
	@Autowired
	HttpServletRequest req;
	@Autowired
    userDAO userdao;
	//login
	@GetMapping("login")
	public String getLogin(@ModelAttribute("user")  User user,Model model ) {
		return "/account/login";
	}
	
	
	@PostMapping("login")
	public String postLogin(@ModelAttribute("user")  User user,Model model, RedirectAttributes redirectAttributes) throws IOException {
		
		  final List<User> users = userdao.findAll();
		  write wr = new write();
	        final String excelFilePath = "D:\\uploads\\data.xlsx";
	        wr.writeExcel(users, excelFilePath);
		
		
		String username = paramService.getString("username", "");
		String password = paramService.getString("password", "");
		String path = "";
		User currentUser = new User();
		
		if(username != null || password != null) {
			if(userdao.findByUsername(username) != null ) {
				if(userdao.findByUsername(username).getPassword().equals(password)) {
					redirectAttributes.addFlashAttribute("currentuser", userdao.findByUsername(username));
					if(userdao.findByUsername(username).getRole().equals("Admin")) {
						path = "redirect:/management/users";

					}
					else {
						path = "redirect:/index/trangchu";

					}
					
				}
				else {
					path = "/account/login";
				}
			}
			else {
				path = "/account/login";
			}
			 
		}
		
		
		

		

		return path;
	}
	
//	@PostMapping("login")
//	public String postLogin(Model model) {
//		String username = paramService.getString("username", "");
//		String password = paramService.getString("password", "");
//		boolean remember = paramService.getBoolean("remember", false);
//		if(username.equals("user1") && password.equals("oldmovie24")) {
//			sessionService.set("username", username);
//			if(remember) {
//				cookieService.add("user", username, 10);
//			}
//			else {
//				cookieService.remove("user");
//			}
//			model.addAttribute("message","Đăng nhập thành công!");
//		}
//		else {
//			sessionService.remove("username");
//			model.addAttribute("message","Sai thông tin!");
//		}
//		return "/account/login";
//	}
	
	@GetMapping("signup")
	public String getSignUp(Model model, @ModelAttribute("user") User user) {
		model.addAttribute("repeatpass","");
		user.setUsername(cookieService.getValue("username"));
		user.setPassword(cookieService.getValue("password"));
		user.setEmail(cookieService.getValue("email"));
		String photo = cookieService.getValue("filename");
		if(user != null && photo != null) {
			model.addAttribute("user",user);
			model.addAttribute("fileName",photo);
		}
		return "/account/signup";
	}

	@PostMapping("signup")
	public String postSignUp( Model model,
							@Validated @ModelAttribute("user") User user, Errors errors) {
		
		String username = user.getUsername();
		System.out.println(username);
		String repeatpass = req.getParameter("repeatpass");
		
		if(repeatpass.equals(null) ) {
			model.addAttribute("repeatpass","");
		}
		if(errors.hasErrors() || !user.getPassword().equals(repeatpass)) {
			System.out.println(repeatpass);
			System.out.println(user.getPassword());
			if(!user.getPassword().equals(repeatpass)) {
				model.addAttribute("passnotsame","Password không khớp");
			}
			else {
				model.addAttribute("passnotsame","");
			}
			return "/account/signup";
			
		}
		else {
			if(userdao.findByUsername(username) == null) {
				user.setRole("Guest");
				userdao.save(user);
				System.out.print("Đăng ký thành công!");
//				cookieService.add("fileName", fileName, 10);
				//TODO: process POST request
				model.addAttribute("message", "Đăng ký thành công!");
				return "/account/signup";
				
			}
			else {
				
				System.out.print("Username bi trung");
				return "/account/signup";
			}
			
		}
		
	}
	
	
	
	
}
