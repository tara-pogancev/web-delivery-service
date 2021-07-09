package comparators;

import java.util.Comparator;

import dto.OrderViewDTO;

public class OrderDateComparator implements Comparator<OrderViewDTO> {

	@Override
	public int compare(OrderViewDTO o1, OrderViewDTO o2) {
		return o1.date.compareTo(o2.date);
	}

}