package repository;

import model.Deliverer;

public class DelivererRepository extends GenericRepository<Deliverer, DelivererRepository> {

	@Override
	protected String getFileName() {
		return "delivererData.json";
	}

	@Override
	protected String getKey(Deliverer e) {
		return e.getId();
	}

}
