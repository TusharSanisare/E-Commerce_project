package controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dto.ProductDTO;
import model.Category;
import sevrice.ProductService;
import sevrice.categoryService;

@Controller
public class adminController {
	
	@Autowired
	categoryService cs;
	
	@Autowired
	ProductService ps;

	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	
	@GetMapping("/admin/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", cs.getAllCategory());
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getCategoriesAdd(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String postCategoriesAdd(@ModelAttribute("category") Category cat) {
		cs.addCategory(cat);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategories(@PathVariable int id) {
		cs.revomeCategoryById(id);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategories(@PathVariable int id, Model model) {
		Optional<Category> cat = cs.getCategoryById(id);
		if(cat.isPresent()) {
			model.addAttribute("category", cat.get());
			return "categoriesAdd";
		}else {
			return "404";
		}
	}
	
	
	// product Section
	@GetMapping("/admin/products")
	public String getProducts(Model model) {
		model.addAttribute("products", ps.getAllProduct());
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String productAddGet(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", cs.getAllCategory());
		return "productsAdd";
	}
	
	@PostMapping("/admin/products/add")
	public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO, @RequestParam("productImage")MultipartFile file, @RequestParam("imgName")String imgName) throws IOException{
		return "productsAdd";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
