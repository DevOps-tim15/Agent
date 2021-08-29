package uns.ac.rs.reportservice.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import uns.ac.rs.reportservice.domain.Product;
import uns.ac.rs.reportservice.dto.ReportDTO;
import uns.ac.rs.reportservice.exception.InvalidDataException;
import uns.ac.rs.reportservice.repository.OrderItemRepository;
import uns.ac.rs.reportservice.repository.ProductRepository;
import uns.ac.rs.reportservice.service.ReportService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportIT {
	
	@Autowired
	private ReportService reportService;
	
	@MockBean
	private OrderItemRepository orderItemRepository;
	
	@MockBean
	private ProductRepository productRepository;
	
	@Test
	public void earningsReport() throws InvalidDataException {
		ReportDTO rep1 = new ReportDTO(1L, 10.0);
		ReportDTO rep2 = new ReportDTO(2L, 300.0);
		ReportDTO rep3 = new ReportDTO(3L, 120.0);
		
		ArrayList<ReportDTO> list = new ArrayList<>();
		list.add(rep1);
		list.add(rep2);
		list.add(rep3);
		
		when(orderItemRepository.sumTotalEarnings()).thenReturn(list);
		when(productRepository.findById(1L)).thenReturn(Optional.of(new Product("p1", 100.0, 100, null)));
		when(productRepository.findById(2L)).thenReturn(Optional.of(new Product("p2", 15.0, 20, null)));
		when(productRepository.findById(3L)).thenReturn(Optional.of(new Product("p3", 10.0, 30, null)));
		assertEquals(3, reportService.productEarnings().size());
	}
	
	@Test(expected = InvalidDataException.class)
	public void createProductEmptyName() throws InvalidDataException {
		ReportDTO rep1 = new ReportDTO(1L, 10.0);
		ReportDTO rep2 = new ReportDTO(2L, 300.0);
		ReportDTO rep3 = new ReportDTO(3L, 120.0);
		
		ArrayList<ReportDTO> list = new ArrayList<>();
		list.add(rep1);
		list.add(rep2);
		list.add(rep3);
		
		when(orderItemRepository.sumTotalEarnings()).thenReturn(list);
		when(productRepository.findById(1L)).thenReturn(Optional.of(new Product("p1", 100.0, 100, null)));
		when(productRepository.findById(2L)).thenReturn(Optional.empty());
		when(productRepository.findById(3L)).thenReturn(Optional.of(new Product("p3", 10.0, 30, null)));
		reportService.productEarnings();
	}
	
	

}
