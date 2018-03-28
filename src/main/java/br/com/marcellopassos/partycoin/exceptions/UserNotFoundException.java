package br.com.marcellopassos.partycoin.exceptions;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = -762217123049121918L;
	
	public static final String USER_NOT_FOUND = "User not found!";

	public UserNotFoundException() {
		super(USER_NOT_FOUND);
	}

	public UserNotFoundException(String username) {
		super(USER_NOT_FOUND + " " + username);
	}

}
