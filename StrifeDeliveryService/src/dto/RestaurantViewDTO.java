package dto;

import model.Restaurant;

public class RestaurantViewDTO {
	
	public String name;
	public String type; 
	public String status;
	public String city;
	public String postal;
	public String address;
	public String rating;	
		
	public RestaurantViewDTO (Restaurant r) {
		this.name = r.getName();
		this.type = r.getType();
		this.status = r.getStatus().toString();
		this.city = r.getLocation().getCity();
		this.postal = r.getLocation().getPostalCode();
		this.address = r.getLocation().getAddressName();
		this.rating = r.getRating();		
	}
	
	public RestaurantViewDTO() {
		this.name = "NO_RESTAURANT";
		this.type = null;
		this.status = null;
		this.city = null;
		this.postal = null;
		this.address = null;
		this.rating = null;		
	}

}
