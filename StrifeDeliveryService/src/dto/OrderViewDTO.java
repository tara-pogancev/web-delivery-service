package dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import model.Order;

public class OrderViewDTO {

	public String id;
	public String status;
	public String restaurantName;
	public String customer;
	public float price;
	public String date;
	
	public OrderViewDTO(Order order) {
		super();
		
		this.id = order.getId();
		this.status = order.getStatus().toString();
		this.restaurantName = order.getRestaurant().getName();
		this.price = order.getPrice();
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy, hh:mm");  
		this.date = dateFormat.format(order.getDateAndTime());
		
		this.customer = order.getCustomerId();
		
	}
	
}
