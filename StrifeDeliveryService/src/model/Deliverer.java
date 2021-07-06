package model;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import enumeration.Gender;
import enumeration.UserCategory;

@XmlRootElement
public class Deliverer extends User {

	private ArrayList<String> ordersToDeliver;

	public Deliverer(String id, String password, String name, String lastName, Gender gender, Date dateOfBirth,
			UserCategory category) {
		super(id, password, name, lastName, gender, dateOfBirth, category);
		this.ordersToDeliver = new ArrayList<String>();
		this.setCategory(UserCategory.DELIVERER);
	}

	public ArrayList<String> getOrdersToDeliver() {
		return ordersToDeliver;
	}

	public void setOrdersToDeliver(ArrayList<String> ordersToDeliver) {
		this.ordersToDeliver = ordersToDeliver;
	}

	public void addOrder(String orderId) {
		if (!this.ordersToDeliver.contains(orderId))
			this.ordersToDeliver.add(orderId);		
	}

	public void removeOrder(String orderId) {
		if (this.ordersToDeliver.contains(orderId))
			this.ordersToDeliver.remove(orderId);	
		
	}

}
