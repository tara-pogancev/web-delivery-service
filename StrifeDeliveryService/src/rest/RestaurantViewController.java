package rest;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.RestaurantDTO;
import dto.RestaurantViewDTO;
import dto.SearchFilterDTO;
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
	
	private Restaurant getCurrentRestaurant() {
		repo.setBasePath(getDataDirPath());

		return repo.read((String) ctx.getAttribute("restaurant"));
	}
	
	@POST
	@Path("setCurrentRestaurant")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void getFilteredSearch(RestaurantDTO dto) {
		
		ctx.setAttribute("restaurant", dto.name);
	}
	

}
