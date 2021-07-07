package rest;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.CommentViewDTO;
import dto.OrderViewDTO;
import model.Comment;
import model.Order;
import repository.CommentRepository;

@Path("comments")
public class CommentController {
	CommentRepository repoComment = new CommentRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("currentComment") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("currentComment", new CommentRepository());
		}
	}
	
	private String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data"
				+ File.separator);
	}
	
	@GET
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
}
