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

import dto.ProductDTO;
import model.IdGenerator;
import model.Product;
import repository.ProductRepository;

@Path("products")
public class ProductController {

	ProductRepository repo = new ProductRepository();

	@Context
	ServletContext ctx;

	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("product") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("product", new ProductController());
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

		repo.create(product);
		System.out.println("Product " + product.getName() + " created.");

		return product.getId();
	}

}
