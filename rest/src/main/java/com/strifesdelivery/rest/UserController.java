package com.strifesdelivery.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import enumeration.Gender;
import enumeration.UserCategory;
import model.User;

@Path("users")
public class UserController {
		
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser()
	{

		User tempUser = new User("1", "123", "Tara", "Valentine", Gender.FEMALE, "2000-02-03", UserCategory.ADMIN);
		return tempUser.getId();
	}

}
