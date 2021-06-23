package repository;

import model.*;

public class RestaurantRepository extends GenericRepository<Restaurant, RestaurantRepository> {

	@Override
	protected String getFileName() {
		return "restaurantData.json";
	}

	@Override
	protected String getKey(Restaurant e) {
		return e.getName();
	}

}
