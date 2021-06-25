package dto;

import enumeration.RestaurantStatus;
import model.Restaurant;

public class RestaurantDTO {

	public String name;
	public String type; 
	public String status;
	public String city;
	public String postal;
	public String address;
	public String rating;
	
	public RestaurantStatus getEnumStatus() {
		if (this.status.equals("open"))
			return RestaurantStatus.OPEN;
		else return 
				RestaurantStatus.CLOSED;
	}
		
	public RestaurantDTO (Restaurant r) {
		this.name = r.getName();
		this.type = r.getType();
		this.status = r.getStatus().toString();
		this.city = r.getLocation().getCity();
		this.postal = r.getLocation().getPostalCode();
		this.address = r.getLocation().getAddressName();
		this.rating = r.getRatingString();		
	}

}
