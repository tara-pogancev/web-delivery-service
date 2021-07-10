package comparators;

import java.util.Comparator;

import dto.OrderViewDTO;

public class OrderRestaurantNameComparator implements Comparator<OrderViewDTO> {

	@Override
	public int compare(OrderViewDTO o1, OrderViewDTO o2) {
		return o1.restaurantName.compareTo(o2.restaurantName);
	}
	
}