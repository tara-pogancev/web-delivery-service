package dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import model.User;

public class UserViewDTO {
	public String id;
	public String password;
	public String name;
	public String lastName;
	public String gender;
	public String dateOfBirth;
	
	public UserViewDTO(User user) {
		super();
		this.id = user.getId();
		this.password = user.getPassword();
		this.name = user.getName();
		this.lastName = user.getLastName();
		this.gender = user.getGender().toString().toLowerCase();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);  
		
		this.dateOfBirth = dateFormat.format(user.getDateOfBirth());	
				
	}
}
