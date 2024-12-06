package com.asm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.model.DB;


@Controller
@RequestMapping("item")
public class ItemController {
	@RequestMapping("index")
	public String list(Model model) {
		model.addAttribute("items",DB.items.values());
		return "/shoppingcart/item";
	}
	
}
