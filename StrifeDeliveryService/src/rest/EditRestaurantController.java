package rest;

import java.io.File;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.RestaurantDTO;
import model.Address;
import model.Restaurant;
import repository.RestaurantRepository;

@Path("editRestaurant")
public class EditRestaurantController {
	
	RestaurantRepository repo = new RestaurantRepository();
	Restaurant restaurant;
	
	@Context
	ServletContext ctx;
	
	public void init() {
		if (ctx.getAttribute("currentRestaurant") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("currentRestaurant", new Restaurant());
		}
	}
	
	private String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data" + File.separator);
	}
	
	private Restaurant getCurrentRestaurant() {
		return (Restaurant) ctx.getAttribute("currentRestaurant");
	}
	
	private void setCurrentRestaurant(Restaurant r) {
		ctx.setAttribute("currentRestaurant", r);
	}
	
	@POST
	@Path("editBasicParams")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void editBasicParams(RestaurantDTO dto)
	{
		repo.setBasePath(getDataDirPath());
		restaurant = repo.readByName(dto.name);
		if(dto.type != null) 
			restaurant.setType(dto.type);
		if(dto.status != null)
			restaurant.setStatus(dto.getEnumStatus());
		
		if(dto.address != null && dto.city != null && dto.postal != null) {
		
			Address address = new Address(dto.address, dto.city, dto.postal);
			restaurant.setLocation(address);
		}
		
		setCurrentRestaurant(restaurant);
		repo.update(restaurant);
		
	}
	
}	
