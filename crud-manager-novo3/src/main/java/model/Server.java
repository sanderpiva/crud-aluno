package model;

public class Server {
	private int id;
	private String name;
	private String address;
	private String telephone;
	private String email;
	private Company company;
	
	public Server() {
		this(0);
	}
	
	public Server(int id) {
		this.id = id;
		setName("");
		setAddress("");
		setTelephone("");
		setEmail("");
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public void setEmail(String email) {
		this.email = email;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
