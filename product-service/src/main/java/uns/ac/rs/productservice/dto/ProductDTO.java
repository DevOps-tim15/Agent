package uns.ac.rs.productservice.dto;

public class ProductDTO {
	
	private Long id;
	
	private String name;
	
	private Double price;
	
	private Integer quantity;
	
	private String picture;

	public ProductDTO(Long id, String name, Double price, Integer quantity, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.picture = picture;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", picture="
				+ picture + "]";
	}
	
	
}
