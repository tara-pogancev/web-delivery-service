package model;

import javax.xml.bind.annotation.XmlRootElement;

import repository.EmailRepository;

@XmlRootElement
public class Email {

	private String id;
	private String name;
	private String email;
	private String content;
	
	public Email(String name, String email, String content) {
		super();
		this.name = name;
		this.email = email;
		this.content = content;
		
		EmailRepository repo = new EmailRepository();
		this.id = IdGenerator.getInstance().generateId(repo.getKeySet(), 5);
	}
	
	public Email(){
		
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setGeneratedId(EmailRepository repo) {
		this.id = IdGenerator.getInstance().generateId(repo.getKeySet(), 5);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

		
}
