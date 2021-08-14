package uns.ac.rs.productservice.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uns.ac.rs.productservice.domain.Product;
import uns.ac.rs.productservice.dto.ProductDTO;
import uns.ac.rs.productservice.exception.InvalidDataException;
import uns.ac.rs.productservice.mapper.ProductMapper;
import uns.ac.rs.productservice.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts() {
		return productRepository.findByActive(true);
//		return ProductMapper.fromEntityList(productRepository.findAll(), ProductMapper::fromEntity);
	}

	public Product  createProduct(ProductDTO productDTO) throws InvalidDataException {
		if (productDTO.getName().isEmpty()) {
			throw new InvalidDataException("Name must not be empty!");
		} else if(Stream.of(productDTO.getName(), productDTO.getPrice(), productDTO.getQuantity(), productDTO.getPicture()).anyMatch(Objects::isNull)) {
			throw new InvalidDataException("Some data is missing");
		}
		
		String name = productDTO.getName();
		Product prod = productRepository.findOneByName(name).orElse(null);
		if (prod != null) {
			throw new InvalidDataException("The specified name is already taken!");
		}
		Product saveProd = ProductMapper.toEntity(productDTO);

		return productRepository.save(saveProd);
	}

	public Product updateProduct(Long id, ProductDTO productDTO) throws InvalidDataException {
		
		Optional<Product> productExists = productRepository.findById(id);
		if (!productExists.isPresent()) {
			throw new InvalidDataException("This product doesn't exist!");
		}
		else if (productDTO.getName().isEmpty()) {
			throw new InvalidDataException("Name must not be empty!");
		} else if(Stream.of(productDTO.getName(), productDTO.getPrice(), productDTO.getQuantity(), productDTO.getPicture()).anyMatch(Objects::isNull)) {
			throw new InvalidDataException("Some data is missing");
		}
		
		Product product = productExists.get();
		Product prodByName = productRepository.findOneByName(productDTO.getName()).orElse(null);
		if (prodByName != null && !prodByName.getName().equals(product.getName())) {
			throw new InvalidDataException("The specified name is already taken!");
		}
		
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setQuantity(productDTO.getQuantity());
		product.setPicture(productDTO.getPicture());
		return productRepository.save(product);
	}

	public void delete(Long id) throws InvalidDataException {
		Product product = productRepository.findById(id).orElse(null);
		if (product == null) {
			throw new InvalidDataException("This product doesn't exist!");
		}
		product.setActive(false);
		productRepository.save(product);
	}

	public Product getProduct(Long id) throws InvalidDataException {
		Product product = productRepository.findById(id).orElse(null);
		if (product == null) {
			throw new InvalidDataException("This product doesn't exist!");
		}
		return product;
	}

}
