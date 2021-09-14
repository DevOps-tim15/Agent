package uns.ac.rs.reportservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import uns.ac.rs.reportservice.service.ReportService;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://agent-gateway-stage.herokuapp.com", "http://agent-gateway-production.herokuapp.com"})
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("productEarnings")
	public ResponseEntity<?> highestProductEarnings() {
		try {
			return new ResponseEntity<>(reportService.productEarnings(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("productCount")
	public ResponseEntity<?> numberOfSoldProducts() {
		try {
			return new ResponseEntity<>(reportService.productCount(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
