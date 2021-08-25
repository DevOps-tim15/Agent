package uns.ac.rs.shoppingservice.mapper;

import uns.ac.rs.shoppingservice.domain.Order;
import uns.ac.rs.shoppingservice.dto.OrderDTO;


public class OrderMapper extends AbstractMapper {
	
	public static Order toEntity(OrderDTO orderDTO) {
		Order order = new Order();
		order.setAdderss(orderDTO.getAddress());
		order.setFirstName(orderDTO.getFirstName());
		order.setLastName(orderDTO.getLastName());
		order.setTotalPrice(orderDTO.getTotalPrice());
		return order;
		
	}
}