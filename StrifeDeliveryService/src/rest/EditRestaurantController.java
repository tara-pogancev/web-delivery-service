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

import dto.ProductDTO;
import dto.RestaurantDTO;
import dto.UserDTO;
import model.Address;
import model.Manager;
import model.Product;
import model.Restaurant;
import repository.ManagerRepository;
import repository.ProductRepository;
import repository.RestaurantRepository;

@Path("editRestaurant")
public class EditRestaurantController {

	RestaurantRepository repo = new RestaurantRepository();
	ProductRepository repoProduct = new ProductRepository();
	ManagerRepository repoManager = new ManagerRepository();
	Restaurant restaurant;

	@Context
	ServletContext ctx;

	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("currentRestaurant") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("currentRestaurant", new Restaurant());
		}
	}

	private String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data"
				+ File.separator);
	}

	@SuppressWarnings("unused")
	private Restaurant getCurrentRestaurant() {
		return (Restaurant) ctx.getAttribute("currentRestaurant");
	}
	
	@POST
	@Path("setRestaurantById")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void setRestaurantById(RestaurantDTO dto) {
		repo.setBasePath(getDataDirPath());
		restaurant = repo.readByName(dto.name);
		setCurrentRestaurant(restaurant);
	}


	private void setCurrentRestaurant(Restaurant r) {
		ctx.setAttribute("currentRestaurant", r);
	}

	@POST
	@Path("editBasicParams")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void editBasicParams(RestaurantDTO dto) {
		repo.setBasePath(getDataDirPath());
		restaurant = repo.readByName(dto.name);
		if (dto.type != null)
			restaurant.setType(dto.type);
		if (dto.status != null)
			restaurant.setStatus(dto.getEnumStatus());

		if (dto.address != null && dto.city != null && dto.postal != null) {

			Address address = new Address(dto.address, dto.city, dto.postal);
			restaurant.setLocation(address);
		}

		setCurrentRestaurant(restaurant);
		repo.update(restaurant);

	}

	@POST
	@Path("addProduct")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addProduct(ProductDTO dto) {
		repo.setBasePath(getDataDirPath());

		Restaurant restaurant = repo.readByName(dto.name);
		restaurant.addProduct(dto.id);

		repo.update(restaurant);
	}

	@POST
	@Path("getRestaurantItems")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Product> getRestaurantItems(RestaurantDTO dto) {
		repo.setBasePath(getDataDirPath());
		repoProduct.setBasePath(getDataDirPath());		

		Restaurant restaurant = repo.read(dto.name);
		ArrayList<Product> retVal = new ArrayList<Product>();
		
		for (String pId : restaurant.getProducts()) {
			Product p = repoProduct.read(pId);
			if (!p.isDeleted())
				retVal.add(p);
		}

		return retVal;
	}
	
	@POST
	@Path("deleteRestaurant")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteRestaurant(RestaurantDTO dto) {
		repo.setBasePath(getDataDirPath());
		Restaurant r = repo.read(dto.name);
		r.setDeleted(true);
		repo.update(r);
		System.out.println("Restaurant " + dto.name + " deleted.");
	}
	
	@POST
	@Path("addManager")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addManager(UserDTO dto) {
		repoManager.setBasePath(getDataDirPath());
		
		Restaurant restaurant = getCurrentRestaurant();
		Manager m = repoManager.read(dto.id);
		m.setRestaurantId(restaurant.getName());
		
		repoManager.update(m);
	}

}
