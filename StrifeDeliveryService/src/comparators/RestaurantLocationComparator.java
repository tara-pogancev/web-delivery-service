package comparators;

import java.util.Comparator;

import dto.RestaurantViewDTO;

public class RestaurantLocationComparator implements Comparator<RestaurantViewDTO> {

	@Override
	public int compare(RestaurantViewDTO o1, RestaurantViewDTO o2) {
		return o1.address.compareTo(o2.address);
	}
}
