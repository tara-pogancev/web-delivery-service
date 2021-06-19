package model;

import java.util.ArrayList;

public class Cart {

	private ArrayList<CartItem> items;
	private Customer cartOwener;
	private float totalPrice;
	
	public Cart(ArrayList<CartItem> items, Customer cartOwener, float totalPrice) {
		super();
		this.items = items;
		this.cartOwener = cartOwener;
		this.totalPrice = totalPrice;
	}
	public ArrayList<CartItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<CartItem> items) {
		this.items = items;
	}
	public Customer getCartOwener() {
		return cartOwener;
	}
	public void setCartOwener(Customer cartOwener) {
		this.cartOwener = cartOwener;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	//TODO logika za dodavanje elementa i brisanje elementa iz korpe
	
	
}
