package uns.ac.rs.reportservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uns.ac.rs.reportservice.domain.OrderItem;
import uns.ac.rs.reportservice.dto.ReportDTO;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	@Query(value = "select new uns.ac.rs.reportservice.dto.ReportDTO(item.product.id, sum(item.totalPrice)) from OrderItem AS item GROUP BY item.product")
	List<ReportDTO> sumTotalEarnings();

	@Query(value = "select new uns.ac.rs.reportservice.dto.ReportDTO(item.product.id, sum(item.quantity*1.0)) from OrderItem AS item GROUP BY item.product")
	List<ReportDTO> sumTotalCount();
}
