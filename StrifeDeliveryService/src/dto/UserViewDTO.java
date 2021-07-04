package dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import model.Customer;
import model.User;

public class UserViewDTO {
	public String id;
	public String password;
	public String name;
	public String lastName;
	public String gender;
	public String dateOfBirth;
	public String customerStatus;
	public boolean blocked;
	public int points;
	public int discount;
	
	public UserViewDTO(User user) {
		super();
		this.id = user.getId();
		this.password = user.getPassword();
		this.name = user.getName();
		this.lastName = user.getLastName();
		this.gender = user.getGender().toString().toLowerCase();
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);  		
		this.dateOfBirth = dateFormat.format(user.getDateOfBirth());	
				
	}
	
	public UserViewDTO(Customer user) {
		super();
		this.id = user.getId();
		this.password = user.getPassword();
		this.name = user.getName();
		this.lastName = user.getLastName();
		this.gender = user.getGender().toString().toLowerCase();
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);  
		
		this.dateOfBirth = dateFormat.format(user.getDateOfBirth());	
		
		this.points = user.getPoints();
		this.customerStatus = user.getCustomerType().getName();
		this.blocked = user.isBlocked();
		this.discount = user.getCustomerType().getDicount();
				
	}
}
