package rest;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.RestaurantDTO;
import dto.RestaurantViewDTO;
import model.Restaurant;
import repository.RestaurantRepository;

@Path("restaurant")
public class RestaurantController {
	
	RestaurantRepository repo = new RestaurantRepository();
	
	@Context
	ServletContext ctx;

	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("newRestaurant") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("newRestaurant", new RestaurantRepository());
		}
	}
	
	private String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data" + File.separator);
	}
	
	@GET
	@Path("getAll")	
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Restaurant> getAllRestaurants()
	{
		repo.setBasePath(getDataDirPath());
					
		return repo.getAll();				
	}
	
	@GET
	@Path("getAllDTO")	
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<RestaurantViewDTO> getAllRestaurantsDTO()
	{
		repo.setBasePath(getDataDirPath());
		ArrayList<RestaurantViewDTO> retVal = new ArrayList<RestaurantViewDTO>();
		
		for (Restaurant r : repo.getAll())
			retVal.add(new RestaurantViewDTO(r));
					
		return retVal;				
	}

}
