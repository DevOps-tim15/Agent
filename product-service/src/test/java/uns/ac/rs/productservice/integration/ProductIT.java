package uns.ac.rs.productservice.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import uns.ac.rs.productservice.domain.Product;
import uns.ac.rs.productservice.dto.ProductDTO;
import uns.ac.rs.productservice.exception.InvalidDataException;
import uns.ac.rs.productservice.mapper.ProductMapper;
import uns.ac.rs.productservice.repository.ProductRepository;
import uns.ac.rs.productservice.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIT {
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Autowired
	private ProductService productService;
	
	@MockBean
    private ProductRepository productRepository;
	
	@Test
	public void createProduct() throws InvalidDataException {
		ProductDTO prodDTO = new ProductDTO(1L, "name", 200.20, 100, "picture");
		Product prod = ProductMapper.toEntity(prodDTO);
		when(productRepository.findOneByName(prodDTO.getName())).thenReturn(Optional.empty());	
		when(productRepository.save(Mockito.any(Product.class))).thenReturn(prod);	
		assertEquals(prod, productService.createProduct(prodDTO));
	}
	
	@Test(expected = InvalidDataException.class)
	public void createProductEmptyName() throws InvalidDataException {
		ProductDTO prodDTO = new ProductDTO();
		prodDTO.setPrice(100.00);
		prodDTO.setQuantity(10);
		prodDTO.setPicture("12334");
		when(productRepository.findOneByName(prodDTO.getName())).thenReturn(Optional.empty());
		productService.createProduct(prodDTO);
	}

}
