package model;

import java.util.ArrayList;
import java.util.Date;

import enumeration.Gender;
import enumeration.UserCategory;

public class Customer extends User {

	private ArrayList<Order> orders;
	private Cart cart;
	private int points;
	
	public Customer(String id, String password, String name, String lastName, Gender gender, Date dateOfBirth,
			UserCategory category, Cart cart, int points) {
		super(id, password, name, lastName, gender, dateOfBirth, category);
		this.orders = new ArrayList<Order>();
		this.cart = cart;
		this.points = points;
	}
	
	
	public ArrayList<Order> getOrders() {
		return orders;
	}
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	
}
