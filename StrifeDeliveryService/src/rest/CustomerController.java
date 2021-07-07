package rest;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

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
import dto.UserViewDTO;
import enumeration.OrderStatus;
import model.Customer;
import model.Order;
import model.User;
import repository.CustomerRepository;
import repository.OrderRepository;

@Path("customers")
public class CustomerController {

	CustomerRepository repoCustomer = new CustomerRepository();
	OrderRepository repoOrder = new OrderRepository();

	@Context
	ServletContext ctx;

	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("customers") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("customers", new CustomerController());
		}
	}

	public String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data"
				+ File.separator);
	}

	@GET
	@Path("getAllCustomers")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<UserViewDTO> getAll() {
		repoCustomer.setBasePath(getDataDirPath());

		ArrayList<UserViewDTO> retVal = new ArrayList<UserViewDTO>();

		for (Customer c : repoCustomer.getAll())
			retVal.add(new UserViewDTO((Customer) c));

		System.out.println("Found " + retVal.size() + " customres.");

		return retVal;
	}

	@POST
	@Path("banUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public void banUser(UserDTO user) {
		repoCustomer.setBasePath(getDataDirPath());

		if (repoCustomer.read(user.id) != null)
			repoCustomer.ban(user.id);
	}

	@POST
	@Path("unbanUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public void unbanUser(UserDTO user) {
		repoCustomer.setBasePath(getDataDirPath());

		if (repoCustomer.read(user.id) != null)
			repoCustomer.unban(user.id);
	}

	@POST
	@Path("getFilteredSearch")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<UserViewDTO> getFilteredSearch(SearchFilterDTO dto) {

		repoCustomer.setBasePath(getDataDirPath());

		ArrayList<UserViewDTO> retVal = new ArrayList<UserViewDTO>();

		for (Customer c : repoCustomer.getAll())
			if (validateSearchUser(c, dto))
				retVal.add(new UserViewDTO((Customer) c));

		System.out.println(retVal.size() + " customers found.");

		return retVal;
	}

	private boolean validateSearchUser(User user, SearchFilterDTO dto) {
		if (user.getId().toLowerCase().contains(dto.text.toLowerCase())
				|| user.getName().toLowerCase().contains(dto.text.toLowerCase())
				|| user.getLastName().toLowerCase().contains(dto.text.toLowerCase()) || dto.text.isEmpty())
			return true;

		return false;
	}

	@GET
	@Path("getSusCustomers")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<UserViewDTO> getSusCustomers() {
		repoCustomer.setBasePath(getDataDirPath());

		ArrayList<UserViewDTO> retVal = new ArrayList<UserViewDTO>();

		for (Customer c : repoCustomer.getAll())
			if (canceledOrders(c) >= 10)
				retVal.add(new UserViewDTO((Customer) c));

		return retVal;
	}

	private int canceledOrders(Customer c) {
		repoOrder.setBasePath(getDataDirPath());
		int retVal = 0;

		for (Order o : repoOrder.getAllByCustomer(c.getId())) {
			if (o.getStatus() == OrderStatus.CANCELED) {
				Date orderDate = o.getDateAndTime();
				LocalDate date = orderDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate currDate = LocalDate.now();

				if (date.isAfter(currDate.minusDays(30)))
					retVal++;
			}
		}
		
		return retVal;
	}

}
