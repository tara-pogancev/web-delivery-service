package repository;

import model.*;

public class CustomerRepository extends GenericRepository<Customer, CustomerRepository> 
{

	@Override
	protected String getFileName() {		
		return "customerData.json";
	}

	@Override
	protected String getKey(Customer e) {
		return e.getId();
	}
	
}
