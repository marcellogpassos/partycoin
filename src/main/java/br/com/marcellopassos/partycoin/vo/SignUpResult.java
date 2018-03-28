package br.com.marcellopassos.partycoin.vo;

public class SignUpResult {

	private final String username;
	
	public static final String SUCCESS_MESSAGE = "User created successfully!";

	public SignUpResult(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
	public String getMessage() {
		return SignUpResult.SUCCESS_MESSAGE;
	}

}
