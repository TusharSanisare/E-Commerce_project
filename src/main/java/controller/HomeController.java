package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import sevrice.ProductService;
import sevrice.categoryService;

@Controller
public class HomeController {

	@Autowired
	categoryService cs;
	
	@Autowired
	ProductService ps;
	
	@GetMapping({"/", "/home"})
	public String home(Model model) {
		return "index";
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", cs.getAllCategory());
		model.addAttribute("products", ps.getAllProduct());
		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(@PathVariable int id, Model model) {
		model.addAttribute("categories", cs.getAllCategory());
		model.addAttribute("products", ps.getAllProductByCategoryId(id));
		return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewproduct(@PathVariable Long id, Model model) {
		model.addAttribute("product", ps.getProductById(id).get());
		return "viewProduct";
	}
	
}
