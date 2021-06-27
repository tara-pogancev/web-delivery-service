package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


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
		if (ctx.getAttribute("passwords") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("emails", new EmailRepository());
		}
	}
	
	@POST
	@Path("/checkUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendEmail(Email emailPar)
	{			
		for(Admin a : repoAdmin.getAll()) {
			if (a.getPassword() == )
		}
	}
}


