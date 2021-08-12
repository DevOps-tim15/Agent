package uns.ac.rs.productservice.mapper;

import uns.ac.rs.productservice.domain.Product;
import uns.ac.rs.productservice.dto.ProductDTO;

public class ProductMapper extends AbstractMapper {
	
	public static ProductDTO fromEntity(Product product) {
		return new ProductDTO(product.getName(), product.getPrice(), product.getQuantity(), product.getPicture());
	}
	
	public static Product toEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setQuantity(productDTO.getQuantity());
		product.setPicture(productDTO.getPicture());
		product.setActive(true);
		return product;	
		
	}
	
	
}
