package uns.ac.rs.shoppingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.shoppingservice.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
}