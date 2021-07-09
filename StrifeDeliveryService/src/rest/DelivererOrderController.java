package rest;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import comparators.OrderDateComparator;
import comparators.OrderPriceComparator;
import dto.OrderDTO;
import dto.OrderSearchFilterDTO;
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

	@GET
	@Path("getActiveOrders")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<OrderViewDTO> getActiveOrders() {
		repoOrder.setBasePath(getDataDirPath());
		repoDel.setBasePath(getDataDirPath());
		ArrayList<OrderViewDTO> retVal = new ArrayList<>();

		for (String o : getActiveDeliverer().getOrdersToDeliver()) {
			Order order = repoOrder.read(o);
			if (order.getStatus() == OrderStatus.TRANSPORT)
				retVal.add(new OrderViewDTO(order));
		}

		return retVal;
	}

	@GET
	@Path("getAllOrders")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<OrderViewDTO> getAllOrders() {
		repoOrder.setBasePath(getDataDirPath());
		repoDel.setBasePath(getDataDirPath());
		ArrayList<OrderViewDTO> retVal = new ArrayList<>();

		for (String o : getActiveDeliverer().getOrdersToDeliver()) {
			Order order = repoOrder.read(o);
			if (order.getStatus() == OrderStatus.TRANSPORT || order.getStatus() == OrderStatus.DELIVERED)
				retVal.add(new OrderViewDTO(order));
		}

		return retVal;
	}

	@POST
	@Path("requestOrder")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void requestOrder(OrderDTO dto) {
		repoDel.setBasePath(getDataDirPath());
		repoOrder.setBasePath(getDataDirPath());

		Deliverer current = getActiveDeliverer();

		if (repoOrder.read(dto.id).getStatus() == OrderStatus.AWAITING_DELIVERER)
			current.addOrder(dto.id);

		repoDel.update(current);

	}

	@POST
	@Path("deliverOrder")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deliverOrder(OrderDTO dto) {
		repoOrder.setBasePath(getDataDirPath());

		Order o = repoOrder.read(dto.id);
		o.setStatus(OrderStatus.DELIVERED);
		repoOrder.update(o);

	}

	@POST
	@Path("getFilteredSearchDel")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<OrderViewDTO> getFilteredSearchCustomer(OrderSearchFilterDTO dto) {
		repoOrder.setBasePath(getDataDirPath());
		repoDel.setBasePath(getDataDirPath());
		ArrayList<OrderViewDTO> retVal = new ArrayList<>();

		for (String oId : getActiveDeliverer().getOrdersToDeliver()) {
			Order o = repoOrder.read(oId);
			if (o.getStatus() == OrderStatus.TRANSPORT || o.getStatus() == OrderStatus.DELIVERED)
				if (filterStatus(dto.status, o) && filterPriceMin(dto.priceMin, o) && filterPriceMax(dto.priceMax, o)
						&& filterDateEnd(dto.endDate, o) && filterDateStart(dto.startDate, o)
						&& filterRestType(dto.restType, o) && filterRestName(dto.searchField, o))

					retVal.add(new OrderViewDTO(o));
		}

		// System.out.println(dto.toString());

		return sortList(retVal, dto.sort);
	}

	private boolean filterStatus(String status, Order o) {
		if (status == null || status.equals("all"))
			return true;

		return (((o.getStatus()).toString()).equals(status));
	}

	private boolean filterPriceMin(Float price, Order o) {
		if (price == 0)
			return true;

		return (price <= o.getPrice());
	}

	private boolean filterPriceMax(Float price, Order o) {
		if (price == 0)
			return true;

		return (price >= o.getPrice());
	}

	private boolean filterDateStart(Date date, Order o) {
		if (date == null)
			return true;

		return (date.before(o.getDateAndTime()));
	}

	private boolean filterDateEnd(Date date, Order o) {
		if (date == null)
			return true;

		return (date.after(o.getDateAndTime()));
	}

	private boolean filterRestType(String type, Order o) {
		if (type == null || type.equals("all"))
			return true;

		return ((o.getRestaurant().getType()).equals(type));
	}

	private boolean filterRestName(String search, Order o) {
		if (search == null || search.isEmpty())
			return true;

		return ((o.getRestaurant().getName().toUpperCase()).contains(search.toUpperCase()));
	}

	private ArrayList<OrderViewDTO> sortList(ArrayList<OrderViewDTO> list, String sort) {

		switch (sort) {
		case "PriceASC":
			list = priceASC(list);
			break;

		case "PriceDES":
			list = priceDES(list);
			break;

		case "DateASC":
			list = dateASC(list);
			break;

		case "DateDES":
			list = dateDES(list);
			break;

		default:
			break;
		}

		return list;
	}

	private ArrayList<OrderViewDTO> priceASC(ArrayList<OrderViewDTO> list) {
		Collections.sort(list, new OrderPriceComparator());
		return list;
	}

	private ArrayList<OrderViewDTO> priceDES(ArrayList<OrderViewDTO> list) {
		Collections.sort(list, new OrderPriceComparator());
		Collections.reverse(list);
		return list;
	}

	private ArrayList<OrderViewDTO> dateASC(ArrayList<OrderViewDTO> list) {
		Collections.sort(list, new OrderDateComparator());
		return list;
	}

	private ArrayList<OrderViewDTO> dateDES(ArrayList<OrderViewDTO> list) {
		Collections.sort(list, new OrderDateComparator());
		Collections.reverse(list);
		return list;
	}

}
