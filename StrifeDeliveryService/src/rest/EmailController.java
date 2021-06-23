package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


import model.*;
import repository.EmailRepository;

@Path("email")
public class EmailController {
	
	private EmailRepository repo = new EmailRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("emails") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("emails", new EmailRepository());
		}
	}
	
	@POST
	@Path("/sendEmail")
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendEmail(Email emailPar)
	{			
		repo.setBasePath(getDataDirPath());
		
		emailPar.setId(repo);
		System.out.println("Sending email from " + emailPar.getName() + "...");		
				
		repo.create(emailPar);		
		System.out.println("Email sent!");	
		
	}
		
	public String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF\\classes\\data\\");
	}

}
