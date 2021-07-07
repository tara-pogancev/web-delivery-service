package rest;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.RestaurantViewDTO;
import dto.SearchFilterDTO;
import enumeration.RestaurantStatus;
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
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data"
				+ File.separator);
	}

	@GET
	@Path("getAllOpen")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<RestaurantViewDTO> getAllRestaurantsOpen() {
		repo.setBasePath(getDataDirPath());
		ArrayList<RestaurantViewDTO> retVal = new ArrayList<>();

		for (Restaurant r : repo.getAll())
			if (r.getStatus().equals(RestaurantStatus.OPEN) && !r.isDeleted())
				retVal.add(new RestaurantViewDTO(r));

		return retVal;
	}

	@GET
	@Path("getAllClosed")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<RestaurantViewDTO> getAllRestaurantsClosed() {
		repo.setBasePath(getDataDirPath());
		ArrayList<RestaurantViewDTO> retVal = new ArrayList<>();

		for (Restaurant r : repo.getAll())
			if (r.getStatus().equals(RestaurantStatus.CLOSED) && !r.isDeleted())
				retVal.add(new RestaurantViewDTO(r));

		return retVal;
	}

	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Restaurant> getAllRestaurants() {
		repo.setBasePath(getDataDirPath());

		return repo.getAllValid();
	}

	@GET
	@Path("getAllDTO")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<RestaurantViewDTO> getAllRestaurantsDTO() {
		repo.setBasePath(getDataDirPath());
		ArrayList<RestaurantViewDTO> retVal = new ArrayList<RestaurantViewDTO>();

		for (Restaurant r : repo.getAllValid())
			retVal.add(new RestaurantViewDTO(r));

		return retVal;
	}

	@POST
	@Path("getFilteredSearch")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<RestaurantViewDTO> getFilteredSearch(SearchFilterDTO dto) {

		repo.setBasePath(getDataDirPath());

		ArrayList<RestaurantViewDTO> retVal = new ArrayList<RestaurantViewDTO>();

		for (Restaurant r : repo.getAllValid())
			if (validateSearchRestaurant(r, dto))
				retVal.add(new RestaurantViewDTO(r));

		System.out.println(retVal.size() + " restaurants found.");

		return retVal;
	}

	private boolean validateSearchRestaurant(Restaurant r, SearchFilterDTO dto) {
		if (dto.selection.equals("all") || r.getType().equals(dto.selection)) {
			if (r.getName().toLowerCase().contains(dto.text.toLowerCase())
					|| r.getLocation().getCity().toLowerCase().contains(dto.text.toLowerCase())
					|| r.getLocation().getAddressName().toLowerCase().contains(dto.text.toLowerCase())
					|| r.getRatingString().toLowerCase().contains(dto.text.toLowerCase()) || dto.text.isEmpty()) {

				if (dto.checkbox)
					if (r.getStatus().equals(RestaurantStatus.CLOSED))
						return false;

				return true;

			}

		}

		return false;
	}

}
