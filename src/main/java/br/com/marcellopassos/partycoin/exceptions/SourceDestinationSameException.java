package br.com.marcellopassos.partycoin.exceptions;

public class SourceDestinationSameException extends Exception {

	private static final long serialVersionUID = -8641483541944969546L;

	public static final String SOURCE_DESTINATION_SAME = "Source and destination wallets are the same!";

	public SourceDestinationSameException() {
		super(SOURCE_DESTINATION_SAME);
	}

	public SourceDestinationSameException(String msg) {
		super(msg);
	}

}
