package uns.ac.rs.reportservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.reportservice.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
