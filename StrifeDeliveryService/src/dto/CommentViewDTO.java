package dto;

import enumeration.CommentState;
import model.Comment;
import model.Customer;
import model.Restaurant;

public class CommentViewDTO {

	private String id;
	private Customer author; 
	private Restaurant restaurant; 
	private String text;
	private int rating;
	private CommentState state = CommentState.WAITING;
	
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
