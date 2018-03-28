package br.com.marcellopassos.partycoin.exceptions;

public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = -6884896135961288873L;

	public static final String INSUFFICIENT_FUNDS = "Insufficient funds!";

	public InsufficientFundsException() {
		super(INSUFFICIENT_FUNDS);
	}

	public InsufficientFundsException(String msg) {
		super(msg);
	}

}
