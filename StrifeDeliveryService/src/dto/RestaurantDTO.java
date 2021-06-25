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
	
	public RestaurantStatus getEnumStatus() {
		if (this.status.equals("open"))
			return RestaurantStatus.OPEN;
		else return 
				RestaurantStatus.CLOSED;
	}


}
