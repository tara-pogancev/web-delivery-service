package repository;

import model.CustomerType;

public class CustomerTypeRepository extends GenericRepository<CustomerType, CustomerTypeRepository> {

	@Override
	protected String getFileName() {
		return "customerTypeData.json";
	}

	@Override
	protected String getKey(CustomerType e) {
		return e.getName();
	}

}
