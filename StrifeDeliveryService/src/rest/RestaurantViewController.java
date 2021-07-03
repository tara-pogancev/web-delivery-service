package rest;

import java.io.File;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.RestaurantDTO;
import dto.RestaurantViewDTO;
import model.Restaurant;
import repository.RestaurantRepository;

@Path("restaurantView")
public class RestaurantViewController {

	RestaurantRepository repo = new RestaurantRepository();

	@Context
	ServletContext ctx;

	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("restaurant") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("restaurant", new String());
		}
	}

	private String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data"
				+ File.separator);
	}

	@GET
	@Path("getCurrentRestaurant")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RestaurantViewDTO getCurrentRestaurant() {
		repo.setBasePath(getDataDirPath());

		Restaurant current = repo.read((String) ctx.getAttribute("restaurant"));
		//System.out.println("Fetching " + current.getName() + "...");
		return new RestaurantViewDTO(current);
	}

	@POST
	@Path("setCurrentRestaurant")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void getFilteredSearch(RestaurantDTO dto) {
		//System.out.println("Restaurant set with parameter: " + dto.name);
		ctx.setAttribute("restaurant", dto.name);
	}

}
