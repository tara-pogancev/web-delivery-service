package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

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
	
	@GET
	@Path("findUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public String userLogIn(String username, String password) {
		String message = "err: not getting into repo";
		User foundUser;
		for(User u : repoCustomer.getAll()) 
		{
			if (u.getId() == username) {
				if(u.getPassword() == password) {
					foundUser = u;
					ctx.setAttribute("loggedin", foundUser);
					message = "Log in successful";
					break;
				}else {
					message = "Wrong password";
					break;
				}
			}else {
				message = "Username not found";
				break;
			}
		}
		return message;
	}
}


