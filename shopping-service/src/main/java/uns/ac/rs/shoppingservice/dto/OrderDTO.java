package uns.ac.rs.shoppingservice.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private Double totalPrice;
	
	private List<ItemDTO> items = new ArrayList<ItemDTO>();

	public OrderDTO(String firstName, String lastName, String address, Double totalPrice, List<ItemDTO> items) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.totalPrice = totalPrice;
		this.items = items;
	}

	public OrderDTO() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<ItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderDTO [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", totalPrice="
				+ totalPrice + ", items=" + items + "]";
	}

}
