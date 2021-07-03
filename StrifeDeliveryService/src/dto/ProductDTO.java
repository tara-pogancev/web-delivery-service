package dto;

import enumeration.ProductType;

public class ProductDTO {
	
	public String id;
	public String name;
	public float price;
	public String type;
	public int quantity = 0;
	public String description = "";
	public boolean deleted = false;
	
	public ProductType getEnumType() {
		if (this.type.equals("FOOD"))
			return ProductType.FOOD;
		else return 
				ProductType.DRINK;
	}

}
