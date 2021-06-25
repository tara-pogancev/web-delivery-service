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
		this.rating = r.getRatingString();		
	}

}
