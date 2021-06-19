package model;

import java.util.Date;

import enumeration.Gender;
import enumeration.UserCategory;

public class Admin extends User {

	public Admin(String id, String password, String name, String lastName, Gender gender, Date dateOfBirth,
			UserCategory category) {
		super(id, password, name, lastName, gender, dateOfBirth, category);
		// TODO Auto-generated constructor stub
	}

}
