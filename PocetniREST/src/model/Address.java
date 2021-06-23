package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Address {

	private String addressName;	//ulica i broj
	private String city;
	private int postalCode;	
	
	public Address(String addressName, String city, int postalCode) {
		super();
		this.addressName = addressName;
		this.city = city;
		this.postalCode = postalCode;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	
	
	
	
	
}
