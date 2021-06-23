package com.strifesdelivery.rest;

import java.io.File;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.*;
import repository.EmailRepository;

@Path("email")
public class EmailController {
	
	private EmailRepository repo = new EmailRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("emails") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("emails", new EmailRepository());
		}
	}
	
	@POST
	@Path("/sendEmail")
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendEmail(String par)
	{			
		//repo.setBasePath(ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data" + File.separator);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Email emailPar = null;
		try {
			emailPar = objectMapper.readValue(par, Email.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} 
		
		emailPar.setId(repo);
		System.out.println("Sending email from " + emailPar.getName() + "...");		
				
		repo.create(emailPar);		
		System.out.println("Email sent!");	
		
	}
	
	public String getDataDirPath() {
		System.out.println(ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data" + File.separator);
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data" + File.separator);
	}

}