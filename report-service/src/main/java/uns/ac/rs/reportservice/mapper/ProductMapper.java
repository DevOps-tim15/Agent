package uns.ac.rs.reportservice.mapper;

import uns.ac.rs.reportservice.domain.Product;
import uns.ac.rs.reportservice.dto.ProductDTO;

public class ProductMapper {
	
	public static ProductDTO fromEntity(Product product) {
		return new ProductDTO(product.getId(),product.getName());
	}
	
}
