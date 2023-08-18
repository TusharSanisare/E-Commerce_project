package sevrice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Product;
import repository.productRepository;

@Service
public class ProductService {

	@Autowired
	productRepository pr;
	
	public List<Product> getAllProduct(){
		return pr.findAll();
	}
	public void addProduct(Product product){
		pr.save(product);
	}
	public void removeProductById(Long id){
		pr.deleteById(id);
	}
	public Optional<Product> getProductById(Long id){
		return pr.getProductById(id);
	}
	public List<Product> getAllProductByCategoryId(int id){
		return pr.findAllByCategory(id);
	}
}
