package com.strifesdelivery.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import enumeration.Gender;
import enumeration.UserCategory;
import model.Customer;
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
		System.out.println("Creating new customer");
		
		Customer c1 = new Customer("1", "123", "Tara", "Valentine", Gender.FEMALE, "2000-02-03", UserCategory.CUSTOMER);
		repo.create(c1);
						
		return "New customer added.";
	}
	
	
}
