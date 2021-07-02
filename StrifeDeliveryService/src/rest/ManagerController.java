package rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.UserDTO;
import model.Manager;
import repository.ManagerRepository;

@Path("manager")
public class ManagerController {

	ManagerRepository repo = new ManagerRepository();

	@Context
	ServletContext ctx;

	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("customers") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("customers", new ManagerRepository());
		}
	}
	
	public String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data" + File.separator);
	}
	
	@POST
	@Path("unique")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String uniqueUsername(UserDTO par)
	{
		repo.setBasePath(getDataDirPath());
						
		List<Manager> list = repo.getAll();
		
		for (Manager m : list)
			if (m.getId().equals(par.id))
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
				
		Manager manager = new Manager(par.id, par.password, par.name, par.lastName, par.gerGenderEnum(), par.dateOfBirth, null, "");
		repo.create(manager);
		
		System.out.println("Created new manager: " + manager.getId());
		
	}
	
	@GET
	@Path("getAvailableManagers")	
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Manager> getAvailableManagers()
	{
		repo.setBasePath(getDataDirPath());
			
		return repo.getAvailableManagers();		
		
	}
	
	

}
