package model;

import javax.xml.bind.annotation.XmlRootElement;

import enumeration.ProductType;

@XmlRootElement
public class Product {
	
	private String id;
	private String name;
	private float price;
	private ProductType type;
	private int quantity; //u g ili ml
	private String description;
	private boolean deleted = false;
	
	public Product(String id, String name, float price, ProductType type, Restaurant restaurant, int quantity,
			String description) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.quantity = quantity;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
