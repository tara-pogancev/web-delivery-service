package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CartItem {

	private Product product;
	private int amount;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public CartItem(Product product, int amount) {
		super();
		this.product = product;
		this.amount = amount;
	}
		
	
}
