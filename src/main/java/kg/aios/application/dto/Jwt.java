package kg.aios.application.dto;

public class Jwt {
	private String token;

	public Jwt(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
