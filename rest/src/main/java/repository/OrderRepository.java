package repository;

import model.*;

public class OrderRepository extends GenericRepository<Order, OrderRepository> {

	@Override
	protected String getFileName() {
		return "orderData.json";
	}

	@Override
	protected String getKey(Order e) {
		return e.getId();
	}
	
	

}
