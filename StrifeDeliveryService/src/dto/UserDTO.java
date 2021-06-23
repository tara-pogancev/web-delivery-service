package dto;

import java.util.Date;

import enumeration.Gender;

public class UserDTO {

	public String id;
	public String password;
	public String name;
	public String lastName;
	public String gender;
	public Date dateOfBirth;	
	
	public Gender gerGenderEnum() {
		if (gender.equals("male"))
			return Gender.MALE;
		else
			return Gender.FEMALE;
	}
	
}
