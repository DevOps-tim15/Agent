package uns.ac.rs.shoppingservice.dto;

public class ItemDTO {

	private Long productID;
	
	private Integer quantity;
	
	private Double totalPrice;

	public ItemDTO() {
		super();
	}

	public ItemDTO(Long productID, Integer quantity, Double totalPrice) {
		super();
		this.productID = productID;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "ItemDTO [productID=" + productID + ", quantity=" + quantity + ", totalPrice=" + totalPrice + "]";
	}

}
