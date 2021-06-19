package model;

import java.util.ArrayList;
import java.util.Date;

import enumeration.Gender;
import enumeration.UserCategory;

public class Deliverer extends User {

	private ArrayList<Order> ordersToDeliver;

	public Deliverer(String id, String password, String name, String lastName, Gender gender, Date dateOfBirth,
			UserCategory category) {
		super(id, password, name, lastName, gender, dateOfBirth, category);
		this.ordersToDeliver = new ArrayList<Order>();
	}

	public ArrayList<Order> getOrdersToDeliver() {
		return ordersToDeliver;
	}

	public void setOrdersToDeliver(ArrayList<Order> ordersToDeliver) {
		this.ordersToDeliver = ordersToDeliver;
	}
	
	

}
