package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Product;

public interface productRepository extends JpaRepository <Product,Long>{

	Optional<Product> getProductById(Long id);

	List<Product> findAllByCategory(int id);

	List<Product> findAllByCategory_Id(int id);

}
