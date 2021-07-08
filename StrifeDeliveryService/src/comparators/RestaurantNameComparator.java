package comparators;

import java.util.Comparator;

import dto.RestaurantViewDTO;

public class RestaurantNameComparator implements Comparator<RestaurantViewDTO> {

	@Override
	public int compare(RestaurantViewDTO o1, RestaurantViewDTO o2) {
		return o1.name.compareTo(o2.name);
	}

}
