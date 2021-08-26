package uns.ac.rs.shoppingservice.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
import uns.ac.rs.shoppingservice.service.OrderService;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderIT {
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Autowired
	private OrderService orderService;
	
	@MockBean
    private ProductRepository productRepository;
	
	@MockBean
    private OrderRepository orderRepository;
	
	@Test
	public void createOrder() throws InvalidDataException {
		String image = "Product image";
		Product product = new Product(1L, "Shirt", 10.0, 5, image.getBytes(), true);
		List<ItemDTO> itemsDTO = new ArrayList<>();
		ItemDTO itemDTO = new ItemDTO(1L, 3, 30.0);
		itemsDTO.add(itemDTO);
		OrderDTO orderDTO = new OrderDTO("Pera", "Peric", "Adresa 15", 30.0, itemsDTO);
		OrderItem item = ItemMapper.toEntity(itemDTO);
		Order order = OrderMapper.toEntity(orderDTO);
		List<OrderItem> items = new ArrayList<>();
		items.add(item);
		order.setItems(items);
		when(productRepository.findById(orderDTO.getItems().get(0).getProductID())).thenReturn(Optional.of(product));	
		when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);	
		assertEquals(order, orderService.createOrder(orderDTO));
	}
	
	@Test(expected = InvalidDataException.class)
	public void createProductEmptyName() throws InvalidDataException {
		String image = "Product image";
		Product product = new Product(1L, "Shirt", 10.0, 1, image.getBytes(), true);
		List<ItemDTO> itemsDTO = new ArrayList<>();
		ItemDTO itemDTO = new ItemDTO(1L, 4, 40.0);
		itemsDTO.add(itemDTO);
		OrderDTO orderDTO = new OrderDTO("Pera", "Peric", "Adresa 15", 30.0, itemsDTO);
		when(productRepository.findById(orderDTO.getItems().get(0).getProductID())).thenReturn(Optional.of(product));
		orderService.createOrder(orderDTO);
	}
}