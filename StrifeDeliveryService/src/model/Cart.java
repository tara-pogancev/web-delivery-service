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
	public String getCartOwnerId() {
		return this.cartOwnerId;
	}
	public void setCartOwner(Customer cartOwner) {
		this.cartOwnerId = cartOwner.getId();
	}
	public float getTotalPrice() {
		this.totalPrice = 0;
		
		for (CartItem i : items) {
			float price = i.getAmount() * i.getProduct().getPrice();
			this.totalPrice+= price;
		}
		
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void addCartItem(CartItem item) {
		
		for (CartItem c : items) {
			if (c.getProduct().getId().equals(item.getProduct().getId())) {
				c.setAmount(c.getAmount()+item.getAmount());
				return;
			}				
		}
		
		items.add(item);	
	}

	public void removeItem(String productId) {
		for (CartItem c : items) {
			if (c.getProduct().getId().equals(productId)) {
				items.remove(c);
				return;
			}				
		}
		
	}

}
