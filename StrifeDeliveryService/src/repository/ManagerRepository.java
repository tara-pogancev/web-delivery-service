package repository;

import model.Manager;

public class ManagerRepository extends GenericRepository<Manager, ManagerRepository> {

	@Override
	protected String getFileName() {
		return "managerData.json";
	}

	@Override
	protected String getKey(Manager e) {
		return e.getId();
	}
	

}
