package model;

public class Server {
	private int id;
	private String name;
	private String telephone;
	private String email;
	private Company company;
	
	public Server() {
		this(0);
	}
	
	public Server(int id) {
		this.id = id;
		setName("");
		setTelephone("");
		setEmail("");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

}
