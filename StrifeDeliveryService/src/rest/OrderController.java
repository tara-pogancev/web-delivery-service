package rest;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.OrderDTO;
import dto.OrderViewDTO;
import dto.UserDTO;
import enumeration.OrderStatus;
import model.Cart;
import model.CartItem;
import model.Customer;
import model.IdGenerator;
import model.Order;
import model.Restaurant;
import repository.CartRepository;
import repository.CustomerRepository;
import repository.OrderRepository;
import repository.ProductRepository;
import repository.RestaurantRepository;

@Path("orders")
public class OrderController {

	CustomerRepository repoCustomer = new CustomerRepository();
	CartRepository repoCart = new CartRepository();
	ProductRepository repoProduct = new ProductRepository();
	OrderRepository repoOrder = new OrderRepository();
	RestaurantRepository repoRestaurant = new RestaurantRepository();

	@Context
	ServletContext ctx;

	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("orderUser") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("orderUser", new String());
		}
	}

	public String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data"
				+ File.separator);
	}

	@POST
	@Path("setActiveUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void setActiveUser(UserDTO username) {
		System.out.println("Active order username set to: " + username.id);
		ctx.setAttribute("orderUser", username.id);
	}

	@POST
	@Path("makeOrders")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Order> makeOrders() {
		repoCustomer.setBasePath(getDataDirPath());
		repoCart.setBasePath(getDataDirPath());
		repoProduct.setBasePath(getDataDirPath());
		repoOrder.setBasePath(getDataDirPath());
		repoRestaurant.setBasePath(getDataDirPath());

		ArrayList<Order> orders = new ArrayList<>();
		Cart cart = repoCart.read((String) ctx.getAttribute("orderUser"));

		if (cart != null) {

			for (CartItem i : cart.getItems()) {

				Restaurant restaurant = null;
				for (Restaurant r : repoRestaurant.getAll())
					if (r.getProducts().contains(i.getProduct().getId()))
						restaurant = r;

				boolean orderFound = false;
				for (Order o : orders)
					if (o.getRestaurant().getName().equals(restaurant.getName())) {
						o.addCartItem(i);
						orderFound = true;
						break;
					}

				if (orderFound == false) {
					System.out.println("Creating new order.");
					Order o = new Order(null, new ArrayList<CartItem>(), restaurant, null, 0, (String) ctx.getAttribute("orderUser"), OrderStatus.PROCESSING);
					o.setId(IdGenerator.getInstance().generateId(repoOrder.getKeySet(), 10));
					o.addCartItem(i);
					Date date = new Date(System.currentTimeMillis());
					o.setDateAndTime(date);
					o.setPrice();
					
					orders.add(o);
				}

			}
		}
		
		Customer c = repoCustomer.read((String) ctx.getAttribute("orderUser"));		
		
		for (Order order : orders) {
			
			order.applyDiscount(c.getCustomerType().getDiscountFloat());
			repoOrder.create(order);
			
			int points = order.generatePoints();
			c.addPoints(points);
			repoCustomer.update(c);
			
		}
		
		cart.emptyCart();
		repoCart.update(cart);		

		return orders;
	}
	
	@GET
	@Path("getProcessingOrders")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<OrderViewDTO> getProcessingOrders() {
		repoOrder.setBasePath(getDataDirPath());
		ArrayList<OrderViewDTO> retVal = new ArrayList<>();
		
		String customerId = (String) ctx.getAttribute("orderUser");
	
		for (Order o : repoOrder.getAllByCustomer(customerId))
			if (o.getStatus() == OrderStatus.PROCESSING)
				retVal.add(new OrderViewDTO(o));		
		
		return retVal;
	}
	
	@GET
	@Path("getDeliveredOrders")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<OrderViewDTO> getDeliveredOrders() {
		repoOrder.setBasePath(getDataDirPath());
		ArrayList<OrderViewDTO> retVal = new ArrayList<>();
		
		String customerId = (String) ctx.getAttribute("orderUser");
	
		for (Order o : repoOrder.getAllByCustomer(customerId))
			if (o.getStatus() == OrderStatus.DELIVERED)
				retVal.add(new OrderViewDTO(o));		
		
		return retVal;
	}
	
	@GET
	@Path("getAllCustomerOrders")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<OrderViewDTO> getAllCustomerOrders() {
		repoOrder.setBasePath(getDataDirPath());
		ArrayList<OrderViewDTO> retVal = new ArrayList<>();
		
		String customerId = (String) ctx.getAttribute("orderUser");
	
		for (Order o : repoOrder.getAllByCustomer(customerId))
			retVal.add(new OrderViewDTO(o));		
		
		return retVal;
	}
	
	
	@POST
	@Path("cancelOrder")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void cancelOrder(OrderDTO dto) {		
		repoOrder.setBasePath(getDataDirPath());
		
		Order orderToCancel = repoOrder.read(dto.id);
		if (orderToCancel.getStatus() == OrderStatus.PROCESSING)		
			orderToCancel.setStatus(OrderStatus.CANCELED);
		
		repoOrder.update(orderToCancel);			
		System.out.println("Order canceled.");
		
		
	}

}
