package br.com.marcellopassos.partycoin.exceptions;

public class NegativeValueException extends Exception {

	private static final long serialVersionUID = -3846207647189546259L;
	
	public static final String NEGATIVE_VALUE = "Negative value! Not allowed here.";

	public NegativeValueException() {
		super(NEGATIVE_VALUE);
	}

	public NegativeValueException(String msg) {
		super(msg);
	}

}
