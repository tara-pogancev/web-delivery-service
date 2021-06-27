package rest;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.UserDTO;
import model.Deliverer;
import repository.DelivererRepository;

@Path("deliverer")
public class DelivererController {

	DelivererRepository repo = new DelivererRepository();

	@Context
	ServletContext ctx;

	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("customers") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("customers", new DelivererRepository());
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
						
		List<Deliverer> list = repo.getAll();
		
		for (Deliverer d : list)
			if (d.getId().equals(par.id))
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
				
		Deliverer deliverer = new Deliverer(par.id, par.password, par.name, par.lastName, par.gerGenderEnum(), par.dateOfBirth, null);
		repo.create(deliverer);
		
		System.out.println("Created new deliverer: " + deliverer.getId());
		
	}

}
