package uns.ac.rs.shoppingservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uns.ac.rs.shoppingservice.domain.Order;
import uns.ac.rs.shoppingservice.domain.OrderItem;
import uns.ac.rs.shoppingservice.domain.Product;
import uns.ac.rs.shoppingservice.dto.ItemDTO;
import uns.ac.rs.shoppingservice.dto.OrderDTO;
import uns.ac.rs.shoppingservice.exception.InvalidDataException;
import uns.ac.rs.shoppingservice.mapper.ItemMapper;
import uns.ac.rs.shoppingservice.mapper.OrderMapper;
import uns.ac.rs.shoppingservice.repository.OrderRepository;
import uns.ac.rs.shoppingservice.repository.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public Order createOrder(OrderDTO orderDTO) throws InvalidDataException {
		if(Stream.of(orderDTO.getAddress(), orderDTO.getFirstName(), orderDTO.getLastName()).anyMatch(String::isEmpty)) {
			
		}
		else if(Stream.of(orderDTO.getAddress(), orderDTO.getFirstName(), orderDTO.getItems(), 
				orderDTO.getLastName(), orderDTO.getTotalPrice()).anyMatch(Objects::isNull)) {
			throw new InvalidDataException("Some data is missing");
		}
		
		List<OrderItem> items = new ArrayList<OrderItem>();
		for(ItemDTO item : orderDTO.getItems()) {
			Optional<Product> productExists = productRepository.findById(item.getProductID());
			if (!productExists.isPresent()) {
				throw new InvalidDataException("This product doesn't exist!");
			}
			
			Product p = productExists.get();
			if(p.getQuantity() < item.getQuantity()) {
				throw new InvalidDataException("Not enough product on stock!");
			}
			
			else if(p.getQuantity() == item.getQuantity()) {
				p.setActive(false);
				p.setQuantity(0);
			}
			else{
				p.setQuantity(p.getQuantity()-item.getQuantity());
			}
			
			OrderItem orderItem = ItemMapper.toEntity(item);
			orderItem.setProduct(p);
			items.add(orderItem);
		}
		
		Order ord = OrderMapper.toEntity(orderDTO);
		ord.setItems(items);
		
		Order order = orderRepository.save(ord);
		return order;
	}
}
