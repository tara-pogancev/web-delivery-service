package model;

import java.sql.Date;
import java.util.ArrayList;

import enumeration.OrderStatus;

public class Order {
	
	private String id; // 10 karaktera
	private ArrayList<CartItem> items;
	private Restaurant restaurant;
	private Date dateAndTime;	// ??
	private float price;
	private Customer customer;
	private OrderStatus status;
	
	public Order(String id, ArrayList<CartItem> items, Restaurant restaurant, Date dateAndTime, float price,
			Customer customer, OrderStatus status) {
		super();
		this.id = id;
		this.items = items;
		this.restaurant = restaurant;
		this.dateAndTime = dateAndTime;
		this.price = price;
		this.customer = customer;
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
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}


}
