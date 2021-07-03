package model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import enumeration.Gender;
import enumeration.UserCategory;

@XmlRootElement
public class Manager extends User {

	private String restaurantId = "";

	public Manager(String id, String password, String name, String lastName, Gender gender, Date dateOfBirth,
			UserCategory category, String restaurantId) {
		super(id, password, name, lastName, gender, dateOfBirth, category);
		this.setRestaurantId(restaurantId);
		this.setCategory(UserCategory.MANAGER);
	}


	public void setRestaurant(Restaurant restaurant) {
		this.setRestaurantId(restaurant.getName());
	}


	public String getRestaurantId() {
		return restaurantId;
	}


	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	
	
	
	

}
