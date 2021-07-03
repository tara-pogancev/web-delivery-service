package rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import sun.misc.BASE64Decoder;

@Path("images")
public class SaveImageController {

	@Context
	ServletContext ctx;

	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("image") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("image", "");
		}
	}

	private String getDataDirPathLogo() {
		return (ctx.getRealPath("") + "images" + File.separator + "logos" + File.separator);
	}

	private String getDataDirPathMenuItem() {
		return (ctx.getRealPath("") + "images" + File.separator + "menuitems" + File.separator);
	}

	@POST
	@Path("uploadImage")
	public void uploadImage(String name) throws IOException {

		ctx.setAttribute("image", name);
		System.out.println("Image name set to: " + name);

	}

	@POST
	@Path("uploadImageLogo")
	public void saveImageLogo(String input) throws IOException {

		String imageString = input;
		String name = (String) ctx.getAttribute("image");

		BASE64Decoder decoder = new BASE64Decoder();
		byte[] decodedBytes = decoder.decodeBuffer(imageString);

		BufferedImage buffImg = ImageIO.read(new ByteArrayInputStream(decodedBytes));

		File file = new File(getDataDirPathLogo() + name + ".png");
		ImageIO.write(buffImg, "png", file);

		System.out.println("Image " + name + ".png" + " uploaded.");

	}

	@POST
	@Path("uploadImageMenuItem")
	public void saveImageMenuItem(String input) throws IOException {

		String imageString = input;
		String name = (String) ctx.getAttribute("image");

		BASE64Decoder decoder = new BASE64Decoder();
		byte[] decodedBytes = decoder.decodeBuffer(imageString);

		BufferedImage buffImg = ImageIO.read(new ByteArrayInputStream(decodedBytes));

		File file = new File(getDataDirPathMenuItem() + name + ".jpg");
		ImageIO.write(buffImg, "jpg", file);

		System.out.println("Image " + name + ".jpg" + " uploaded.");
	}

}
