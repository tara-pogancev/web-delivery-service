package repository;

import model.*;

public class EmailRepository extends GenericRepository<Email, EmailRepository> {

	@Override
	protected String getFileName() {
		return "emailData.json";
	}

	@Override
	protected String getKey(Email e) {
		return e.getId();
	}

}
