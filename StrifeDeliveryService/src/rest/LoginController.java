package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.UserDTO;
import model.User;
import repository.CustomerRepository;

@Path("login")
public class LoginController {

	private CustomerRepository repoCustomer = new CustomerRepository();
	
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
	@Path("userLogin")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String userLogIn(UserDTO par) {
		User foundUser;
		for(User u : repoCustomer.getAll()) 
		{
			if (u.getId().equals(par.id)) {
				if(u.getPassword().equals(par.password)) {
					foundUser = u;
					ctx.setAttribute("loggedin", foundUser);
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


