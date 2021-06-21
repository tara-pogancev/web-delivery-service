package model;

import javax.xml.bind.annotation.XmlRootElement;

import enumeration.CommentState;

@XmlRootElement
public class Comment {

	private String id;
	private Customer author; 
	private Restaurant restaurant; 
	private String text;
	private int rating;
	private CommentState state = CommentState.WAITING;
	
	
	public Comment(Customer author, Restaurant restaurant, String text, int rating) {
		super();
		this.author = author;
		this.restaurant = restaurant;
		this.text = text;
		this.rating = rating;
		this.id = this.author.getId() + restaurant.getName();

	}

	public Customer getAuthor() {
		return author;
	}

	public void setAuthor(Customer author) {
		this.author = author;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CommentState getState() {
		return state;
	}

	public void setState(CommentState state) {
		this.state = state;
	}
	
	
	
	
}
