package com.asm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asm.model.DB;
import com.asm.model.Item;
import com.asm.service.ShoppingCartService;

import org.springframework.ui.Model;






@Controller
@RequestMapping("cart")
public class ShoppingCartController {
	@Autowired
	ShoppingCartService cart;
	
	@RequestMapping("view")
	public String view( Model model) {
		model.addAttribute("cart", cart);
		model.addAttribute("count", cart.getCount());
		model.addAttribute("totalcount", cart.getTotalCount());
		model.addAttribute("amount", cart.getAmout());
		return "shoppingcart/cart";
	}
	@RequestMapping("add/{id}")
	public String add(@PathVariable("id") Integer id) {
		Item sp = new Item();
		sp = DB.items.get(id);
		cart.add(id, sp);
		return "redirect:/cart/view";
	}
	@RequestMapping("remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		cart.remove(id);
		return "redirect:/cart/view";
	}
	
	@RequestMapping("update/{id}")
	public String update(@PathVariable("id") Integer id, @RequestParam("action") String action, RedirectAttributes redirectAttributes) {
//		cart.update(id, 0);
		if("increase".equals("action")) {
			cart.increaseQuantity(id);
		}else if("decrease".equals("action")) {
			cart.decreaseQuantity(id);
		}
		
		
		return "redirect:/cart/view";
	}
	
	@RequestMapping("clear")
	public String clear(@PathVariable("id") Integer id) {
		cart.clear();
		return "redirect:/cart/view";
	}
}
