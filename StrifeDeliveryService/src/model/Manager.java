package model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import enumeration.Gender;
import enumeration.UserCategory;

@XmlRootElement
public class Manager extends User {

	private Restaurant restaurant;

	public Manager(String id, String password, String name, String lastName, Gender gender, Date dateOfBirth,
			UserCategory category, Restaurant restaurant) {
		super(id, password, name, lastName, gender, dateOfBirth, category);
		this.restaurant = restaurant;
		this.setCategory(UserCategory.MANAGER);
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
	
	
	

}
