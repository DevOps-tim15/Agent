package uns.ac.rs.shoppingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.shoppingservice.domain.Product;

public interface  ProductRepository extends JpaRepository<Product, Long>{
}
