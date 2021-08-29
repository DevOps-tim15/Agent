package uns.ac.rs.reportservice.dto;

public class ReportDTO {
	
	private Long productId;
	private Double total;
	
	public ReportDTO() {
		super();
	}

	public ReportDTO(Long productId, Double total) {
		super();
		this.productId = productId;
		this.total = total;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
