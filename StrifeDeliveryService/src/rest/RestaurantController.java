package rest;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import comparators.*;
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

		return sortList(retVal, dto.sort);
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

	private ArrayList<RestaurantViewDTO> sortList(ArrayList<RestaurantViewDTO> list, String sort) {

		switch (sort) {
		case "NameASC":
			list = nameACS(list);
			break;

		case "NameDES":
			list = nameDES(list);
			break;

		case "LocationASC":
			list = locationACS(list);
			break;

		case "LocationDES":
			list = locationDES(list);
			break;

		case "GradeASC":
			list = gradeACS(list);
			break;

		case "GradeDES":
			list = gradeDES(list);
			break;

		default:
			break;
		}

		return list;
	}

	private ArrayList<RestaurantViewDTO> nameACS(ArrayList<RestaurantViewDTO> list) {
		Collections.sort(list, new RestaurantNameComparator());
		return list;
	}

	private ArrayList<RestaurantViewDTO> nameDES(ArrayList<RestaurantViewDTO> list) {
		Collections.sort(list, new RestaurantNameComparator());
		Collections.reverse(list);
		return list;
	}
	
	private ArrayList<RestaurantViewDTO> locationACS(ArrayList<RestaurantViewDTO> list) {
		Collections.sort(list, new RestaurantLocationComparator());
		return list;
	}

	private ArrayList<RestaurantViewDTO> locationDES(ArrayList<RestaurantViewDTO> list) {
		Collections.sort(list, new RestaurantLocationComparator());
		Collections.reverse(list);
		return list;
	}
	
	private ArrayList<RestaurantViewDTO> gradeACS(ArrayList<RestaurantViewDTO> list) {
		Collections.sort(list, new RestaurantGradeComparator());
		return list;
	}

	private ArrayList<RestaurantViewDTO> gradeDES(ArrayList<RestaurantViewDTO> list) {
		Collections.sort(list, new RestaurantGradeComparator());
		Collections.reverse(list);
		return list;
	}

}
