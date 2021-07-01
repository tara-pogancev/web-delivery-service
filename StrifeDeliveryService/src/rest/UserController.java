package rest;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.SearchFilterDTO;
import dto.UserDTO;
import model.Customer;
import model.Deliverer;
import model.Manager;
import model.User;
import repository.*;

@Path("users")
public class UserController {

	DelivererRepository repoDeliverer = new DelivererRepository();
	CustomerRepository repoCustomer = new CustomerRepository();
	ManagerRepository repoManager = new ManagerRepository();

	@Context
	ServletContext ctx;

	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("users") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("users", new UserController());
		}
	}

	public String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data"
				+ File.separator);
	}

	@GET
	@Path("getAllForAdmin")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getAllForAdmin() {
		repoDeliverer.setBasePath(getDataDirPath());
		repoCustomer.setBasePath(getDataDirPath());
		repoManager.setBasePath(getDataDirPath());

		ArrayList<User> retVal = new ArrayList<User>();

		for (Customer c : repoCustomer.getAll())
			retVal.add((User) c);

		for (Deliverer d : repoDeliverer.getAll())
			retVal.add((User) d);

		for (Manager m : repoManager.getAll())
			retVal.add((User) m);

		return retVal;
	}

	@POST
	@Path("getFilteredSearch")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<User> getFilteredSearch(SearchFilterDTO dto) {
				
		repoDeliverer.setBasePath(getDataDirPath());
		repoCustomer.setBasePath(getDataDirPath());
		repoManager.setBasePath(getDataDirPath());

		ArrayList<User> retVal = new ArrayList<User>();

		for (Customer c : repoCustomer.getAll())
			if (validateSearchUser(c, dto))
				retVal.add((User) c);

		for (Deliverer d : repoDeliverer.getAll())
			if (validateSearchUser(d, dto))
				retVal.add((User) d);

		for (Manager m : repoManager.getAll())
			if (validateSearchUser(m, dto))
				retVal.add((User) m);
		
		System.out.println(retVal.size() + " users found.");

		return retVal;
	}

	private boolean validateSearchUser(User user, SearchFilterDTO dto) {
		if (dto.selection.equals("all") || user.getCategory().toString().equals(dto.selection)) {
			if (user.getId().toLowerCase().contains(dto.text.toLowerCase()) || user.getName().toLowerCase().contains(dto.text.toLowerCase())
					|| user.getLastName().toLowerCase().contains(dto.text.toLowerCase()) || dto.text.isEmpty())
				return true;
		}

		return false;
	}

	@POST
	@Path("deleteUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteUser(UserDTO user) {
		repoDeliverer.setBasePath(getDataDirPath());
		repoCustomer.setBasePath(getDataDirPath());
		repoManager.setBasePath(getDataDirPath());

		if (repoCustomer.read(user.id) != null)
			repoCustomer.delete(user.id);

		if (repoDeliverer.read(user.id) != null)
			repoDeliverer.delete(user.id);

		if (repoManager.read(user.id) != null)
			repoManager.delete(user.id);

	}

}