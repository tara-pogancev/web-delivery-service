package model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import enumeration.RestaurantStatus;

@XmlRootElement
public class Restaurant {

	private String name; 
	private String type; 
	private ArrayList<Product> products;
	private RestaurantStatus status;
	private Location location;
	private boolean deleted = false;
	//SLIKA
	
	public Restaurant(String name, String type, RestaurantStatus status, Location location) {
		super();
		this.name = name;
		this.type = type;
		this.status = status;
		this.location = location;
		this.products = new ArrayList<Product>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RestaurantStatus getStatus() {
		return status;
	}

	public void setStatus(RestaurantStatus status) {
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		if (!this.products.contains(product))
			this.products.add(product);
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
		
}
