package uns.ac.rs.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uns.ac.rs.productservice.dto.ProductDTO;
import uns.ac.rs.productservice.exception.InvalidDataException;
import uns.ac.rs.productservice.service.ProductService;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://agent-gateway-stage.herokuapp.com", "http://agent-gateway-production.herokuapp.com"})
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/getAll")
	public ResponseEntity<?> gettAllProducts(){
		try {
			return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Long id){
		try {
			return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
		} catch (InvalidDataException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {
		try {
			return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.OK);
		} catch (InvalidDataException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
		try {
			System.out.println(productDTO);
			return new ResponseEntity<>(productService.updateProduct(id, productDTO), HttpStatus.OK);
		} catch (InvalidDataException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		try {
			productService.delete(id);
			return new ResponseEntity<String>("Product successfully deleted!", HttpStatus.OK);
		} catch (InvalidDataException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
