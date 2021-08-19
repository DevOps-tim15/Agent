package uns.ac.rs.productservice.mapper;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import uns.ac.rs.productservice.domain.Product;
import uns.ac.rs.productservice.dto.ProductDTO;

public class ProductMapper extends AbstractMapper {
	
	public static ProductDTO fromEntity(Product product) {
		return new ProductDTO(product.getId(),product.getName(), product.getPrice(), product.getQuantity(), new String(product.getPicture(), StandardCharsets.UTF_8));
	}
	
	public static Product toEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setQuantity(productDTO.getQuantity());
		product.setPicture(productDTO.getPicture().getBytes(StandardCharsets.UTF_8));
		product.setActive(true);
		return product;	
		
	}
	
	public static List<ProductDTO> fromEntityList(List<Product> products) {
		List<ProductDTO> productDTOs = new ArrayList<>();
		for(Product product:products) {
			productDTOs.add(new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getQuantity(), new String(product.getPicture(), StandardCharsets.UTF_8)));
		}
		return productDTOs;
	}
}
