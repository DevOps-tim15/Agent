package uns.ac.rs.reportservice.dto;


public class ProductDTO {
	
	private Long id;
	
	private String name;
	
	public ProductDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ProductDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + "]";
	}
}