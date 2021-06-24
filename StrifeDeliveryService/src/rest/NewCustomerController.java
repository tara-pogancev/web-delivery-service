package rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.UserDTO;
import model.Customer;
import repository.CustomerRepository;

@Path("newCustomer")
public class NewCustomerController {

	CustomerRepository repo = new CustomerRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("customers") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("customers", new CustomerRepository());
		}
	}
	
	@POST
	@Path("unique")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String uniqueUsername(UserDTO par)
	{
		repo.setBasePath(getDataDirPath());
		
		System.out.println("Hii " + par.id);
		
		List<Customer> list = repo.getAll();
		
		for (Customer c : list)
			if (c.getId().equals(par.id))
				return "false";
		
		return "true"; 
	}
		
	@POST
	@Path("create")	
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public void createCustomer(UserDTO par)
	{
		repo.setBasePath(getDataDirPath());
		
		Customer customer = new Customer(par.id, par.password, par.name, par.lastName, par.gerGenderEnum(), par.dateOfBirth, null);
		repo.create(customer);
		
		System.out.println("Created new user: " + customer.getId());
		
	}
	
	public String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data" + File.separator);
	}
	
}
