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

import dto.ProductDTO;
import dto.RestaurantDTO;
import model.IdGenerator;
import model.Product;
import model.Restaurant;
import repository.ProductRepository;
import repository.RestaurantRepository;

@Path("products")
public class ProductController {

	ProductRepository repo = new ProductRepository();
	RestaurantRepository restaurantRepo = new RestaurantRepository();

	@Context
	ServletContext ctx;

	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("product") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("product", new String());
		}
	}

	private String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data"
				+ File.separator);
	}

	@POST
	@Path("unique")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String unique(ProductDTO par) {
		repo.setBasePath(getDataDirPath());

		List<Product> list = repo.getAll();

		for (Product p : list)
			if (p.getName().equals(par.name))
				return "false";

		return "true";
	}

	@POST
	@Path("setBasicParams")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String setBasicParams(ProductDTO dto) {
		repo.setBasePath(getDataDirPath());

		Product product = new Product();
		product.setId(IdGenerator.getInstance().generateId(repo.getKeySet(), 5));
		product.setName(dto.name);
		product.setDescription(dto.description);
		product.setType(dto.getEnumType());
		product.setQuantity(dto.quantity);
		product.setPrice(dto.price);

		repo.create(product);
		System.out.println("Product " + product.getName() + " created.");

		return product.getId();
	}
	
	@POST
	@Path("getProductsByRestaurant")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Product> getProductsByRestaurant(RestaurantDTO dto) {
		repo.setBasePath(getDataDirPath());
		restaurantRepo.setBasePath(getDataDirPath());
		
		ArrayList<Product> retVal = new ArrayList<Product>();
		
		Restaurant r = restaurantRepo.readByName(dto.name);
		
		for (String id : r.getProducts()) {
			if (!repo.read(id).isDeleted())
				retVal.add(repo.read(id));
		}

		System.out.println(retVal.size() + " products fetched.");
		
		return retVal;
	}
	
	@POST
	@Path("setActiveProduct")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void setActiveProduct(ProductDTO dto) {
		System.out.println("Active product set to: " + dto.id);
		ctx.setAttribute("product", dto.id);	
	}
	
	@GET
	@Path("getActiveProduct")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Product getActiveProduct() {
		repo.setBasePath(getDataDirPath());
		Product retVal = repo.read((String) ctx.getAttribute("product"));
		System.out.println("Reading product: " + retVal.getName());
		
		return retVal;
	}
	
	@POST
	@Path("editActiveProduct")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void editActiveProduct(ProductDTO dto) {
		repo.setBasePath(getDataDirPath());
		Product retVal = repo.read((String) ctx.getAttribute("product"));
		
		retVal.setName(dto.name);
		retVal.setDescription(dto.description);
		retVal.setPrice(dto.price);
		retVal.setQuantity(dto.quantity);
		retVal.setType(dto.getEnumType());
		
		repo.update(retVal);		
	}
	
	@POST
	@Path("deleteProduct")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteProduct(ProductDTO dto) {
		repo.setBasePath(getDataDirPath());
		Product retVal = repo.read(dto.id);
		retVal.setDeleted(true);		
		repo.update(retVal);		
		System.out.println("Product #" + dto.id + " deleted.");
	}

}
