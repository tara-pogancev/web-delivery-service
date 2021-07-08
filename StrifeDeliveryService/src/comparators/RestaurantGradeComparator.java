package comparators;

import java.util.Comparator;

import dto.RestaurantViewDTO;

public class RestaurantGradeComparator implements Comparator<RestaurantViewDTO> {

	@Override
	public int compare(RestaurantViewDTO o1, RestaurantViewDTO o2) {
		return o1.rating.compareTo(o2.rating);
	}

}