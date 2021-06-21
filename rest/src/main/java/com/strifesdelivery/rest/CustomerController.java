package com.strifesdelivery.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import enumeration.Gender;
import enumeration.UserCategory;
import model.Admin;
import model.Customer;
import repository.AdminRepository;
import repository.CustomerRepository;

@Path("customers")
public class CustomerController {

	CustomerRepository repo = new CustomerRepository();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Customer> getUsers()
	{		
		System.out.println("Getting all customers");		
		return repo.getAll();
	}
	
	
	@GET
	@Path("new")	
	@Produces(MediaType.TEXT_PLAIN)
	public String createCustomer(Customer c)
	{
		Admin a1 = new Admin("admin", "admin", "admin", "Valentine", Gender.FEMALE, "2000-02-03", UserCategory.ADMIN);
		AdminRepository repoa = new AdminRepository();
		repoa.create(a1);
						
		return "New adminy added.";
	}
	
	
}
