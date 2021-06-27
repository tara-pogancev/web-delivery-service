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

import dto.RestaurantDTO;
import dto.UserDTO;
import model.*;
import repository.ManagerRepository;
import repository.RestaurantRepository;

@Path("newRestaurant")
public class NewRestaurantController {

	ManagerRepository repoManager = new ManagerRepository();
	RestaurantRepository repoRestaurant = new RestaurantRepository();
	Restaurant restaurant;	// Context
	
	@Context
	ServletContext ctx;

	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("newRestaurant") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("newRestaurant", new Restaurant());
		}
	}
	
	private String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data" + File.separator);
	}
	
	private Restaurant getCurrentRestaurant() {
		return (Restaurant) ctx.getAttribute("newRestaurant");
	}
	
	private void setCurrentRestaurant(Restaurant r) {
		ctx.setAttribute("newRestaurant", r);
	}
	
	@POST
	@Path("unique")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String uniqueUsername(RestaurantDTO par)
	{
		repoRestaurant.setBasePath(getDataDirPath());
						
		List<Restaurant> list = repoRestaurant.getAll();
		
		for (Restaurant r : list)
			if (r.getName().equals(par.name))
				return "false";
		
		return "true"; 
	}
	
	@POST
	@Path("init")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void initRestaurant()
	{		
		ctx.setAttribute("newRestaurant", new Restaurant());
		
		System.out.println("1 - " + getCurrentRestaurant().toString());
		
	}
	
	@POST
	@Path("setBasicParams")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void setBasicParams(RestaurantDTO dto)
	{
		restaurant = getCurrentRestaurant();
		
		restaurant.setName(dto.name);
		restaurant.setType(dto.type);
		restaurant.setStatus(dto.getEnumStatus());
		
		setCurrentRestaurant(restaurant);
		
		System.out.println("2 - " + getCurrentRestaurant().toString());
		
	}
	
	@POST
	@Path("setAddress")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void setAddress(RestaurantDTO dto)
	{
		restaurant = getCurrentRestaurant();
		
		Address address = new Address(dto.address, dto.city, dto.postal);		
		restaurant.setLocation(address);
		
		setCurrentRestaurant(restaurant);
		
		System.out.println("3 - " + getCurrentRestaurant().toString());
		
	}
	
	@POST
	@Path("setManager")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void setManager(UserDTO dto)
	{
		restaurant = getCurrentRestaurant();
		repoManager.setBasePath(getDataDirPath());
		repoRestaurant.setBasePath(getDataDirPath());
		
		Manager manager = repoManager.read(dto.id);
		
		repoRestaurant.create(restaurant);
		manager.setRestaurant(restaurant);
		
		repoManager.update(manager);	
		
	}
	
	
	
	
	
	
	
	
	
}
