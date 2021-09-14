package uns.ac.rs.shoppingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uns.ac.rs.shoppingservice.dto.OrderDTO;
import uns.ac.rs.shoppingservice.exception.InvalidDataException;
import uns.ac.rs.shoppingservice.service.OrderService;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://agent-gateway-stage.herokuapp.com", "http://agent-gateway-production.herokuapp.com"})
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/test")
	public ResponseEntity<?> test(){
		try {
			return new ResponseEntity<>("Hi there!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody OrderDTO orderDTO) {
		try {
			return new ResponseEntity<>(orderService.createOrder(orderDTO), HttpStatus.OK);
		} catch (InvalidDataException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
