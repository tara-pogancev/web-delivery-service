package rest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.UserDTO;


@Path("images")
public class SaveImageController {
	
	@Context
	ServletContext ctx;

	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("images") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("images", new SaveImageController());
		}
	}	
	
	private String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data" + File.separator);
	}
	
	@POST
	@Path("uploadImage")
	@Consumes(MediaType.TEXT_PLAIN)
	public void uploadImage(String input)
	{
		System.out.println("hi");
		System.out.println(input.length());
		System.out.println("hi2");
		
//		BufferedImage buffImg = input;
//
//		System.out.println(getDataDirPath());
//				
//		File file = new File(getDataDirPath() + "sadImage.jpg");
//		ImageIO.write(buffImg, "jpg", file);
				
	}
	

}
