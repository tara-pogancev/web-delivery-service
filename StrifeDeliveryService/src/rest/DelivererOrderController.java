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

import dto.OrderViewDTO;
import dto.UserDTO;
import enumeration.OrderStatus;
import model.Deliverer;
import model.Order;
import repository.DelivererRepository;
import repository.OrderRepository;

@Path("delOrders")
public class DelivererOrderController {
	DelivererRepository repoDel = new DelivererRepository();
	OrderRepository repoOrder = new OrderRepository();

	@Context
	ServletContext ctx;

	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("deliverer") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("deliverer", new String());
		}
	}

	public String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data"
				+ File.separator);
	}

	@POST
	@Path("setActiveDeliverer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void setActiveDeliverer(UserDTO username) {
		System.out.println("Active deliverer username set to: " + username.id);
		ctx.setAttribute("deliverer", username.id);
	}

	private Deliverer getActiveDeliverer() {
		repoDel.setBasePath(getDataDirPath());
		return repoDel.read((String) ctx.getAttribute("deliverer"));
	}

	@GET
	@Path("getAvailableOrders")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<OrderViewDTO> getAvailableOrders() {
		repoOrder.setBasePath(getDataDirPath());
		repoDel.setBasePath(getDataDirPath());
		ArrayList<OrderViewDTO> retVal = new ArrayList<>();

		Deliverer currentDel = getActiveDeliverer();

		for (Order o : repoOrder.getAllAvailable())
			if (!isRequested(o))
				retVal.add(new OrderViewDTO(o));

		return retVal;
	}

	private boolean isRequested(Order o) {
		repoOrder.setBasePath(getDataDirPath());
		repoDel.setBasePath(getDataDirPath());

		Deliverer currentDel = getActiveDeliverer();

		if (o.getStatus() == OrderStatus.AWAITING_DELIVERER)
			if (currentDel.getOrdersToDeliver().contains(o.getId()))
				return true;

		return false;
	}

}
