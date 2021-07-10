package model;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import enumeration.Gender;
import enumeration.UserCategory;

@XmlRootElement
public class Customer extends User {

	private ArrayList<Order> orders;
	private CustomerType customerType;
	private int points;
		
	
	public Customer(String id, String password, String name, String lastName, Gender gender, Date dateOfBirth,
			UserCategory category) {
		super(id, password, name, lastName, gender, dateOfBirth, category);
		this.setCategory(UserCategory.CUSTOMER);
		this.orders = new ArrayList<Order>();
		this.customerType = new CustomerType("BRONZE", 0, 0);
		this.points = 0;
	}
	
	public Customer(String id, String password, String name, String lastName, Gender gender, String dateOfBirth,
			UserCategory category) {
		super(id, password, name, lastName, gender, dateOfBirth, category);
		this.setCategory(UserCategory.CUSTOMER);
		this.orders = new ArrayList<Order>();
		this.customerType = new CustomerType("BRONZE", 0, 0);
		this.points = 0;
	}
	
	public Customer()
	{
		super();
		this.setCategory(UserCategory.CUSTOMER);
		this.orders = new ArrayList<Order>();
		this.customerType = new CustomerType("BRONZE", 0, 0);
		this.points = 0;
	}
	
	public ArrayList<Order> getOrders() {
		return orders;
	}
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	public int getPoints() {
		return this.points;
	}
	public void setPoints(int points) {
		this.points = points;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType() {
		this.customerType = new CustomerType("BRONZE", 0, 0);
		if (this.points > 200)
			this.customerType = new CustomerType("SILVER", 10, 200);
		if (this.points > 500)
			this.customerType = new CustomerType("GOLD", 10, 500);
	}
	
	public void addPoints(int pointsToAdd) {
		this.points += pointsToAdd;
		
		setCustomerType();	
	}
	
	public void removePoints(int pointsToRemove) {
		this.points -= pointsToRemove;
		if (this.points < 0)
			this.points = 0;
		
		setCustomerType();		
	}
	
	
}
