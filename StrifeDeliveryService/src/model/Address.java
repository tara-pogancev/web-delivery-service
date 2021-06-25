package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Address {

	private String addressName;	//ulica i broj
	private String city;
	private String postalCode;	
	
	public Address(String addressName, String city, String postalCode) {
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Address [addressName=" + addressName + ", city=" + city + ", postalCode=" + postalCode + "]";
	}
		
	
	
}
