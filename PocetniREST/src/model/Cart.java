package model;

import java.util.ArrayList;

public class Cart {

	private ArrayList<CartItem> items;
	private String cartOwnerId;
	private float totalPrice;
	private boolean deleted = false;
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Cart(ArrayList<CartItem> items, String cartOwner, float totalPrice) {
		super();
		this.items = items;
		this.cartOwnerId = cartOwner;
		this.totalPrice = totalPrice;
	}
	
	public Cart(String cartOwner) {
		super();
		this.items = new ArrayList<CartItem>();
		this.cartOwnerId = cartOwner;
		this.totalPrice = 0;
	}
	
	public ArrayList<CartItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<CartItem> items) {
		this.items = items;
	}
	public Customer getCartOwner() {
		// TODO Customer owener
		return null;
	}
	public String getCartOwnerId() {
		return this.cartOwnerId;
	}
	public void setCartOwner(Customer cartOwner) {
		this.cartOwnerId = cartOwner.getId();
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

}
