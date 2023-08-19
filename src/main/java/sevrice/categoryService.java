package sevrice;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Category;
import repository.CategoryRepository;

@Service
//@Transactional
public class categoryService {

	@Autowired
	CategoryRepository cr;
	
	public List<Category> getAllCategory(){
		return cr.findAll();
	}
	
	public void addCategory(Category c) {
		cr.save(c);
	}
	
	public void revomeCategoryById(int id) {
		cr.deleteById(id);
	}
	
//	public Optional<Category> getCategoryById(Category category) {
//		return cr.findAllById(category.getId());
//	}
	
	public Optional<Category> getCategoryById(int id) {
		return cr.findById(id);
	}
}
