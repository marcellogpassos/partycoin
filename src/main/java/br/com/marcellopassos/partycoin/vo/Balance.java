package br.com.marcellopassos.partycoin.vo;

public class Balance {
	
	private final String walletHash;
	
	private final float balance;

	public Balance(String walletHash, float balance) {
		super();
		this.walletHash = walletHash;
		this.balance = balance;
	}

	public String getWalletHash() {
		return walletHash;
	}

	public float getBalance() {
		return balance;
	}
	
}
