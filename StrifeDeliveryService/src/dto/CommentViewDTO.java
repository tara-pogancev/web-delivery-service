package dto;

import enumeration.CommentState;
import model.Comment;
import model.Customer;
import model.Restaurant;

public class CommentViewDTO {

	public String id;
	public Customer author; 
	public Restaurant restaurant; 
	public String text;
	public int rating;
	public CommentState state = CommentState.WAITING;
	
	public CommentViewDTO(Comment com) {
		super();
		
		this.id =com.getId();
		this.author = com.getAuthor();
		this.restaurant = com.getRestaurant();
		this.text = com.getText();
		this.rating = com.getRating();
		this.state = com.getState();
	}
}
