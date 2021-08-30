package uns.ac.rs.shoppingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.shoppingservice.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
