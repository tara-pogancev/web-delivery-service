package rest;

import java.io.File;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.UserDTO;
import model.Customer;
import model.Restaurant;
import model.User;
import repository.CustomerRepository;

@Path("login")
public class LoginController {

	CustomerRepository repoCustomer = new CustomerRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("customers") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("customers", new CustomerRepository());
		}
	}
	
	private String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data" + File.separator);
	}
	
	private void setLoggedInUser(Customer u) {
		ctx.setAttribute("newRestaurant", u);
	}
	
	@POST
	@Path("userLogin")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String userLogIn(UserDTO par) 
	{
		repoCustomer.setBasePath(getDataDirPath());
		for(Customer u : repoCustomer.getAll()) 
		{
			if (u.getId().equals(par.id)) {
				if(u.getPassword().equals(par.password)) {
					setLoggedInUser(u);
					return "Loggin successful";
				}else {
					return "Incorrect password";
				}
			}else {
				return "Username was not found";
			}
		}
		return "";
	}
}


