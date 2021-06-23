package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerType {

	private String name;
	private int discount;	// U procentima - 80%
	private int minPoints;
	
	public CustomerType(String name, int dicount, int minPoints) {
		super();
		this.name = name;
		this.discount = dicount;
		this.minPoints = minPoints;
	} 	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDicount() {
		return discount;
	}
	public void setDicount(int dicount) {
		this.discount = dicount;
	}
	public int getMinPoints() {
		return minPoints;
	}
	public void setMinPoints(int minPoints) {
		this.minPoints = minPoints;
	}

	public float getDiscountFloat() {
		return this.discount/100;
	}
	
}
