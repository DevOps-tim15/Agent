package uns.ac.rs.shoppingservice.mapper;

import uns.ac.rs.shoppingservice.domain.OrderItem;
import uns.ac.rs.shoppingservice.dto.ItemDTO;

public class ItemMapper extends AbstractMapper {
	
	public static OrderItem toEntity(ItemDTO itemDTO) {
		OrderItem item = new OrderItem();
		item.setQuantity(itemDTO.getQuantity());
		item.setTotalPrice(itemDTO.getTotalPrice());
		return item;
	}
}
