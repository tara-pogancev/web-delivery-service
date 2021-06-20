package repository;

import model.*;

public class CustomerRepository extends GenericRepository<Customer, CustomerRepository> 
{

	@Override
	protected String getFileName() {		
		return "testjson.json";
	}

	@Override
	protected String getKey(Customer e) {
		return e.getId();
	}

	
}
