package uns.ac.rs.reportservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uns.ac.rs.reportservice.domain.Product;
import uns.ac.rs.reportservice.dto.ProductDTO;
import uns.ac.rs.reportservice.dto.ReportDTO;
import uns.ac.rs.reportservice.exception.InvalidDataException;
import uns.ac.rs.reportservice.mapper.ProductMapper;
import uns.ac.rs.reportservice.repository.OrderItemRepository;
import uns.ac.rs.reportservice.repository.ProductRepository;

@Service
public class ReportService {

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public Map<String, Double> productEarnings() throws InvalidDataException{
		List<ReportDTO> reports = orderItemRepository.sumTotalEarnings();
		HashMap<String, Double> productsReport = new HashMap<String, Double>();
		for (ReportDTO reportDTO : reports) {
			Product product = productRepository.findById(reportDTO.getProductId()).get();
			if (product == null) {
				throw new InvalidDataException("Product does not exist.");
			}
			productsReport.put(ProductMapper.fromEntity(product).getName(), reportDTO.getTotal());
		}
		return productsReport;
	}

	public Map<String, Double> productCount() throws InvalidDataException{
		List<ReportDTO> reports = orderItemRepository.sumTotalCount();
		HashMap<String, Double> productsReport = new HashMap<String, Double>();
		for (ReportDTO reportDTO : reports) {
			Product product = productRepository.findById(reportDTO.getProductId()).get();
			if (product == null) {
				throw new InvalidDataException("Product does not exist.");
			}
			productsReport.put(ProductMapper.fromEntity(product).getName(), reportDTO.getTotal());
		}
		return productsReport;
	}
}