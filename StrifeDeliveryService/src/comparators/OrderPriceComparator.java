package comparators;

import java.util.Comparator;

import dto.OrderViewDTO;

public class OrderPriceComparator implements Comparator<OrderViewDTO> {

	@Override
	public int compare(OrderViewDTO o1, OrderViewDTO o2) {		
		return (int) (o1.price - o2.price);
	}

}
