package rest;

import java.io.File;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.CartItemDTO;
import dto.UserDTO;
import model.Cart;
import model.CartItem;
import model.Product;
import repository.CartRepository;
import repository.CustomerRepository;
import repository.ProductRepository;

@Path("cart")
public class CartController {

	CustomerRepository repo = new CustomerRepository();
	CartRepository repoCart = new CartRepository();
	ProductRepository repoProduct = new ProductRepository();

	@Context
	ServletContext ctx;

	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("cartUser") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("cartUser", new String());
		}
	}

	public String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data"
				+ File.separator);
	}

	private Cart getActiveCart() {
		repoCart.setBasePath(getDataDirPath());
		return repoCart.read((String) ctx.getAttribute("cartUser"));
	}

	@POST
	@Path("setActiveUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void setActiveUser(UserDTO username) {
		System.out.println("Active cart username set to: " + username.id);
		ctx.setAttribute("cartUser", username.id);
	}

	@POST
	@Path("addCartItem")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addCartItem(CartItemDTO dto) {
		
		System.out.println("adding item");
		
		repoCart.setBasePath(getDataDirPath());
		repoProduct.setBasePath(getDataDirPath());

		Product p = repoProduct.read(dto.productId);
		CartItem item = new CartItem(p, dto.amount);

		if (getActiveCart() != null) {
			Cart c = getActiveCart();
			c.addCartItem(item);
			repoCart.update(c);

			System.out.println("New cart created, item added.");
			return;
		}

		Cart c = new Cart((String) ctx.getAttribute("cartUser"));
		c.addCartItem(item);
		repoCart.create(c);

		System.out.println("Cart item added.");
		return;

	}

}
