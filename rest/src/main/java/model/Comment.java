package model;

public class Comment {

	private Customer author; 
	private Restaurant restaurant; 
	private String text;
	private int rating;
	
	public Comment(Customer author, Restaurant restaurant, String text, int rating) {
		super();
		this.author = author;
		this.restaurant = restaurant;
		this.text = text;
		this.rating = rating;
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
	
	
	
	
}
