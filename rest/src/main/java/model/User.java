package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import enumeration.*;

@XmlRootElement
public class User {

	private String id;
	private String password;
	private String name;
	private String lastName;
	private Gender gender;
	private Date dateOfBirth;
	private UserCategory category;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public UserCategory getCategory() {
		return category;
	}
	public void setCategory(UserCategory category) {
		this.category = category;
	}
	public User(String id, String password, String name, String lastName, Gender gender, Date dateOfBirth,
			UserCategory category) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.category = category;
	}
	
	public User(String id, String password, String name, String lastName, Gender gender, String dateOfBirth,
			UserCategory category) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date yourDate = null;
		try {
			yourDate = sdf.parse(dateOfBirth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.dateOfBirth = yourDate;
		this.category = category;
	}
	
	
	
	
	
}
