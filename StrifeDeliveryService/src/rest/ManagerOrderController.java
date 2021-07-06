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

import dto.DeliveryRequestDTO;
import dto.OrderDTO;
import dto.OrderViewDTO;
import dto.UserDTO;
import enumeration.OrderStatus;
import model.Deliverer;
import model.Manager;
import model.Order;
import repository.DelivererRepository;
import repository.ManagerRepository;
import repository.OrderRepository;
import repository.RestaurantRepository;

@Path("managerOrders")
public class ManagerOrderController {

	ManagerRepository repoManager = new ManagerRepository();
	OrderRepository repoOrder = new OrderRepository();
	RestaurantRepository repoRestaurant = new RestaurantRepository();
	DelivererRepository repoDel = new DelivererRepository();

	@Context
	ServletContext ctx;

	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("manager") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("manager", new String());
		}
	}

	public String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data"
				+ File.separator);
	}

	@POST
	@Path("setActiveManager")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void setActiveManager(UserDTO username) {
		System.out.println("Active manager username set to: " + username.id);
		ctx.setAttribute("manager", username.id);
	}
	
	
	private Manager getActiveManager() {
		repoManager.setBasePath(getDataDirPath()); 
		return repoManager.read((String) ctx.getAttribute("manager"));
	}
	
	
	@GET
	@Path("getProcessOrders")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<OrderViewDTO> getProcessOrders() {
		repoOrder.setBasePath(getDataDirPath());
		ArrayList<OrderViewDTO> retVal = new ArrayList<>();
		
		String restaurantName = getActiveManager().getRestaurantId();
		
		for (Order o : repoOrder.getAllByRestaurant(restaurantName))
			if (o.getStatus() == OrderStatus.PROCESSING)
				retVal.add(new OrderViewDTO(o));				
		
		return retVal;
	}
	
	@GET
	@Path("getPreparationOrders")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<OrderViewDTO> getPreparationOrders() {
		repoOrder.setBasePath(getDataDirPath());
		ArrayList<OrderViewDTO> retVal = new ArrayList<>();
		
		String restaurantName = getActiveManager().getRestaurantId();
		
		for (Order o : repoOrder.getAllByRestaurant(restaurantName))
			if (o.getStatus() == OrderStatus.PREPARATION)
				retVal.add(new OrderViewDTO(o));				
		
		return retVal;
	}	
	
	@GET
	@Path("getAllOrders")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<OrderViewDTO> getAllOrders() {
		repoOrder.setBasePath(getDataDirPath());
		ArrayList<OrderViewDTO> retVal = new ArrayList<>();
		
		String restaurantName = getActiveManager().getRestaurantId();
		
		for (Order o : repoOrder.getAllByRestaurant(restaurantName))
			retVal.add(new OrderViewDTO(o));				
		
		return retVal;
	}
	
	@POST
	@Path("prepareOrder")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void prepareOrder(OrderDTO dto) {		
		repoOrder.setBasePath(getDataDirPath());
		
		Order orderToPrepare = repoOrder.read(dto.id);
		if (orderToPrepare.getStatus() == OrderStatus.PROCESSING)		
			orderToPrepare.setStatus(OrderStatus.PREPARATION);
		
		repoOrder.update(orderToPrepare);			
		System.out.println("Order in preparation.");
	}
	
	@POST
	@Path("readyOrder")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void readyOrder(OrderDTO dto) {		
		repoOrder.setBasePath(getDataDirPath());
		
		Order orderToPrepare = repoOrder.read(dto.id);
		if (orderToPrepare.getStatus() == OrderStatus.PREPARATION)		
			orderToPrepare.setStatus(OrderStatus.AWAITING_DELIVERER);
		
		repoOrder.update(orderToPrepare);			
		System.out.println("Order awaiting deliverer.");
	}
	
	
	@GET
	@Path("getRequests")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<DeliveryRequestDTO> getRequests() {		
		repoOrder.setBasePath(getDataDirPath());
		repoDel.setBasePath(getDataDirPath());
		
		ArrayList<DeliveryRequestDTO> retVal = new ArrayList<DeliveryRequestDTO>();
		Manager m = getActiveManager();
				
		for (Deliverer d : repoDel.getAll()) {
			for (String orderId : d.getOrdersToDeliver()) {
				Order o = repoOrder.read(orderId);
				if (o.getStatus() == OrderStatus.AWAITING_DELIVERER && o.getRestaurant().getName().equals(m.getRestaurantId()))
					retVal.add(new DeliveryRequestDTO(d, o));
					
			}
		}
		
		return retVal;
	}
	
	@POST
	@Path("acceptRequest")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void acceptRequest(OrderDTO dto) {
		repoOrder.setBasePath(getDataDirPath());
		repoDel.setBasePath(getDataDirPath());
		
		String requestId = dto.id;
		String orderId = requestId.substring(0, 10);
		String delId = requestId.substring(10);
		
		System.out.println("Accepting order #" + orderId + " for " + delId);
		
		Order order = repoOrder.read(orderId);
		
		for (Deliverer d : repoDel.getAll()) {
			if (d.getOrdersToDeliver().contains(orderId) && !d.getId().equals(delId)) {
				d.removeOrder(orderId);
				repoDel.update(d);
			}				
		}
		
		order.setStatus(OrderStatus.TRANSPORT);
		repoOrder.update(order);		
	}
	
	@POST
	@Path("declineRequest")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void declineRequest(OrderDTO dto) {
		repoOrder.setBasePath(getDataDirPath());
		repoDel.setBasePath(getDataDirPath());
		
		String requestId = dto.id;
		String orderId = requestId.substring(0, 10);
		String delId = requestId.substring(10);
		
		System.out.println("Declining order #" + orderId + " for " + delId);
				
		Deliverer d = repoDel.read(delId);
		d.removeOrder(orderId);
		repoDel.update(d);
			
	}
	
	
}
