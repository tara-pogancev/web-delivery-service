package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.UserDTO;
import model.*;
import repository.AdminRepository;
import repository.CustomerRepository;
import repository.EmailRepository;
import repository.ManagerRepository;

@Path("login")
public class LoginController {
	
	private AdminRepository repoAdmin = new AdminRepository();
	private CustomerRepository repoCustomer = new CustomerRepository();
	private ManagerRepository repoMenager = new ManagerRepository();
	
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
	@Path("findUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public String userLogIn(String username, String password) {
		String message;
		User foundUser;
		for(User u : repoCustomer.getAll()) 
		{
			if (u.getId() == username) {
				if(u.getPassword() == password) {
					foundUser = u;
					ctx.setAttribute("loggedin", foundUser);
					message = "Log in successful";
				}else {
					message = "Wrong password";
				}
			}else {
				message = "Username not found";
			}
		}
	}
	
	@POST
	@Path("unique")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String uniqueUsername(UserDTO par)
	{
		repoCustomer.setBasePath(getDataDirPath());
						
		List<Customer> list = repoCustomer.getAll();
		
		for (Customer c : list)
			if (c.getId().equals(par.id))
				return "false";
		
		return "true"; 
	}
}


