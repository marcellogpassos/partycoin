package br.com.marcellopassos.partycoin.exceptions;

public class NotAuthorizedException extends Exception {

	private static final long serialVersionUID = -5549141144033196530L;

	public static final String NOT_AUTHORIZED = "Not Authorized! Try another key.";

	public NotAuthorizedException() {
		super(NOT_AUTHORIZED);
	}

	public NotAuthorizedException(String msg) {
		super(msg);
	}

}
