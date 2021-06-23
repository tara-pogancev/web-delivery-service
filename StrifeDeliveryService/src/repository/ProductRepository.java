package repository;

import model.*;

public class ProductRepository extends GenericRepository<Product, ProductRepository> {

	@Override
	protected String getFileName() {
		return "productData.json";
	}

	@Override
	protected String getKey(Product e) {
		return e.getId();
	}

}
