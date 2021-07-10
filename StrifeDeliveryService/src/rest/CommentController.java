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

import dto.CommentDTO;
import dto.CommentViewDTO;
import dto.OrderDTO;
import dto.OrderViewDTO;
import dto.RestaurantDTO;
import enumeration.CommentState;
import enumeration.OrderStatus;
import model.Comment;
import model.Order;
import repository.CommentRepository;
import repository.CustomerRepository;
import repository.OrderRepository;

@Path("comments")
public class CommentController {
	CommentRepository repoComment = new CommentRepository();
	OrderRepository repoOrder = new OrderRepository();
	CustomerRepository repoCustomer = new CustomerRepository();
	Order order;
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("currentOrder") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("currentOrder", "");
		}
	}
	
	private String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data"
				+ File.separator);
	}
	
	@POST
	@Path("setCurrentOrder")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setCurrentOrder(OrderDTO orderDTO) {
		  ctx.setAttribute("currentOrder", orderDTO.id);
	} 
	
	@GET
	@Path("getCurrentOrder")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCurrentOrder() {
		return (String) ctx.getAttribute("currentOrder");
	}
	
	@POST
	@Path("getAllCustomerComments")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<CommentViewDTO> getAllCustomerComments(String username) {
		repoComment.setBasePath(getDataDirPath());
		ArrayList<CommentViewDTO> retVal = new ArrayList<>();
		
		String customerId = username;
	
		for (Comment c : repoComment.getAllByCustomer(customerId))
			retVal.add(new CommentViewDTO(c));		
		
		return retVal;
	}
	
	@POST
	@Path("getRestaurantComments")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Comment> getRestaurantComments(RestaurantDTO restaurantDTO) {
		repoComment.setBasePath(getDataDirPath());
		ArrayList<Comment> list = new ArrayList<Comment>();
		for(Comment c : repoComment.getAllByRestaurant(restaurantDTO.name))
		{
			if (c.getState().equals(CommentState.APPROVED))
			{
				list.add(c);
			}
		}
		return list;

	}
	
	@POST
	@Path("getManagerCommentsReviewed")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Comment> getManagerCommentsReviewed(RestaurantDTO restaurantDTO) {
		repoComment.setBasePath(getDataDirPath());
		ArrayList<Comment> list = new ArrayList<Comment>();
		for(Comment c : repoComment.getAllByRestaurant(restaurantDTO.name))
		{
			if (!c.getState().equals(CommentState.WAITING))
			{
				list.add(c);
			}
		}
		return list;

	}
	
	
	@POST
	@Path("getManagerComments")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Comment> getManagerComments(RestaurantDTO restaurantDTO) {
		repoComment.setBasePath(getDataDirPath());
		ArrayList<Comment> list = new ArrayList<Comment>();
		for(Comment c : repoComment.getAllByRestaurant(restaurantDTO.name))
		{
			if (c.getState().equals(CommentState.WAITING))
			{
				list.add(c);
			}
		}
		return list;
	}
	
	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Comment> getAll() {
		repoComment.setBasePath(getDataDirPath());
		
		return repoComment.getAll();
	}
	
	@POST
	@Path("addComment")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addComment(CommentDTO commentDTO) {
		System.out.println(getCurrentOrder());
		repoOrder.setBasePath(getDataDirPath());
		repoCustomer.setBasePath(getDataDirPath());
		repoComment.setBasePath(getDataDirPath());
		Order order = repoOrder.getById(getCurrentOrder());
		commentDTO.id = order.getId();
		commentDTO.author = repoCustomer.getById(order.getCustomerId());
		commentDTO.restaurant = order.getRestaurant();
		Comment newComment = new Comment(commentDTO);
		repoComment.create(newComment);
		
		
		order.setStatus(OrderStatus.REVIEWED);
		repoOrder.update(order);
		
		System.out.println("Comment added");
		return "Review waiting aproval...";
	}
	
	@POST
	@Path("approveComment")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String approveComment(CommentDTO commentDTO) {
		repoComment.setBasePath(getDataDirPath());
		Comment com = repoComment.getById(commentDTO.id);
		com.setState(CommentState.APPROVED);
		repoComment.update(com);
		
		
		return "Review approved...";
	}
	
	@POST
	@Path("denyComment")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String denyComment(CommentDTO commentDTO) {
		repoComment.setBasePath(getDataDirPath());
		Comment com = repoComment.getById(commentDTO.id);
		com.setState(CommentState.DENIED);
		repoComment.update(com);
		
		
		return "Review denied...";
	}
}
