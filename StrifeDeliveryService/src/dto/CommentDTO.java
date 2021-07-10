package dto;

import enumeration.CommentState;
import model.Customer;
import model.Restaurant;

public class CommentDTO {
	public String id;
	public Customer author; 
	public Restaurant restaurant; 
	public String text;
	public int rating;
	public CommentState state = CommentState.WAITING;
}
