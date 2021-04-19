package ru.sanjar.bank.payload.response;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private String firstName;
	private String secondName;
	private String middleName;

	public JwtResponse(String token, Long id, String username, String email, String firstName, String secondName, String middleName) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.secondName = secondName;
		this.middleName = middleName;
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public String getMiddleName() {
		return middleName;
	}
}
