package uns.ac.rs.productservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.productservice.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	Optional<Product> findOneByName(String name);
	List<Product> findByActive(boolean active);
}
