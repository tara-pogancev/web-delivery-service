package rest;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import enumeration.Gender;
import enumeration.UserCategory;
import model.Admin;
import model.Customer;
import repository.AdminRepository;
import repository.CustomerRepository;

@Path("customers")
public class CustomerController {

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
	
	@GET
	@Path("new222")
	@Produces(MediaType.APPLICATION_XML)
	public List<Customer> getUsers()
	{		
		System.out.println("Getting all customers");		
		return repo.getAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String ga()
	{			
		return "Hi";
	}
	
	
	@GET
	@Path("new")	
	@Produces(MediaType.TEXT_PLAIN)
	public String createCustomer(Customer c)
	{
		Admin a1 = new Admin("1", "admin", "admin", "Valentine", Gender.FEMALE, "2000-02-03", UserCategory.ADMIN);
		AdminRepository repoa = new AdminRepository();
		repoa.setBasePath(ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data" + File.separator);
		repoa.create(a1);
			
		return "Sheeesh.";
	}
	
	public String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data" + File.separator);
	}
	
}
