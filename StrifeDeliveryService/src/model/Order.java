package model;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import enumeration.OrderStatus;

@XmlRootElement
public class Order {
	
	private String id; // 10 karaktera
	private ArrayList<CartItem> items;
	private Restaurant restaurant;
	private Date dateAndTime;	// ??
	private float price;
	private String customerId;
	private OrderStatus status;
	
	public Order(String id, ArrayList<CartItem> items, Restaurant restaurant, Date dateAndTime, float price,
			String customer, OrderStatus status) {
		super();
		this.id = id;
		this.items = items;
		this.restaurant = restaurant;
		this.dateAndTime = dateAndTime;
		this.price = price;
		this.customerId = customer;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<CartItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<CartItem> items) {
		this.items = items;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public float getPrice() {
		this.price = 0;
		
		for (CartItem item : items)
			price += item.getAmount() * item.getProduct().getPrice();
		
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customer) {
		this.customerId = customer;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public void addCartItem(CartItem item) {
		this.items.add(item);
	}


}
