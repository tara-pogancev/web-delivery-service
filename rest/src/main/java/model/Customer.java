package model;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import enumeration.Gender;
import enumeration.UserCategory;

@XmlRootElement
public class Customer extends User {

	private ArrayList<Order> orders;
	private Cart cart;
	private int points;
	private boolean blocked = false;
	
	public Customer(String id, String password, String name, String lastName, Gender gender, Date dateOfBirth,
			UserCategory category) {
		super(id, password, name, lastName, gender, dateOfBirth, category);
		this.orders = new ArrayList<Order>();
		this.cart = new Cart(id);
		this.points = 0;
	}
	
	public Customer(String id, String password, String name, String lastName, Gender gender, String dateOfBirth,
			UserCategory category) {
		super(id, password, name, lastName, gender, dateOfBirth, category);
		this.orders = new ArrayList<Order>();
		this.cart = new Cart(id);
		this.points = 0;
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

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	
}
