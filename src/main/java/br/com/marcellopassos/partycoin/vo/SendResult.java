package br.com.marcellopassos.partycoin.vo;

public class SendResult {

	private final String srcWalletHash;
	private final String dstWalletHash;
	private final float amount;
	private final String transactionHash;

	public SendResult(String srcWalletHash, String dstWalletHash, float amount, String transactionHash) {
		super();
		this.srcWalletHash = srcWalletHash;
		this.dstWalletHash = dstWalletHash;
		this.amount = amount;
		this.transactionHash = transactionHash;
	}

	public String getSrcWalletHash() {
		return srcWalletHash;
	}

	public String getDstWalletHash() {
		return dstWalletHash;
	}

	public float getAmount() {
		return amount;
	}

	public String getTransactionHash() {
		return transactionHash;
	}

}
