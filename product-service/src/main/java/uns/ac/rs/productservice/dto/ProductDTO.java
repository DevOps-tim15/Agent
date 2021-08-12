package uns.ac.rs.productservice.dto;

public class ProductDTO {
	
	private String name;
	
	private Double price;
	
	private Integer quantity;
	
	private String picture;

	public ProductDTO(String name, Double price, Integer quantity, String picture) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.picture = picture;
	}

	public ProductDTO() {
		super();
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
	
}
