package repository;

import model.Admin;

public class AdminRepository extends GenericRepository<Admin, AdminRepository>  {

	@Override
	protected String getFileName() {
		return "adminData.json";
	}

	@Override
	protected String getKey(Admin e) {
		return e.getId();
	}

}
