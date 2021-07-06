package dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import model.Deliverer;
import model.Order;

public class DeliveryRequestDTO {

	public String id;
	public String customer;
	public String deliverer;
	public String delivererId;
	public float price;
	public String date;
	
	public DeliveryRequestDTO(Deliverer del, Order o) {
		super();
		this.id = o.getId();
		this.customer = o.getCustomerId();
		this.deliverer = del.getName() + " " + del.getLastName();
		this.price = o.getPrice();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy, hh:mm");  
		this.date = dateFormat.format(o.getDateAndTime());
		this.delivererId = del.getId();
	}
	
}
