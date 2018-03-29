package br.com.marcellopassos.partycoin.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "blockchain")
public class Transaction {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "src_wallet")
	private String srcWallet;
	@Column(name = "dst_wallet")
	private String dstWallet;

	@Column(nullable = false)
	public float value;

	@Column
	public String hash;
	@Column(name = "previous_hash")
	public String previousHash;

	@Column
	public long timestamp;

	public Transaction() {

	}

	public Transaction(String srcWallet, String dstWallet, float value) {
		super();
		this.srcWallet = srcWallet;
		this.dstWallet = dstWallet;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSrcWallet() {
		return srcWallet;
	}

	public void setSrcWallet(String srcWallet) {
		this.srcWallet = srcWallet;
	}

	public String getDstWallet() {
		return dstWallet;
	}

	public void setDstWallet(String dstWallet) {
		this.dstWallet = dstWallet;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dstWallet == null) ? 0 : dstWallet.hashCode());
		result = prime * result + ((hash == null) ? 0 : hash.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((previousHash == null) ? 0 : previousHash.hashCode());
		result = prime * result + ((srcWallet == null) ? 0 : srcWallet.hashCode());
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
		result = prime * result + Float.floatToIntBits(value);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (dstWallet == null) {
			if (other.dstWallet != null)
				return false;
		} else if (!dstWallet.equals(other.dstWallet))
			return false;
		if (hash == null) {
			if (other.hash != null)
				return false;
		} else if (!hash.equals(other.hash))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (previousHash == null) {
			if (other.previousHash != null)
				return false;
		} else if (!previousHash.equals(other.previousHash))
			return false;
		if (srcWallet == null) {
			if (other.srcWallet != null)
				return false;
		} else if (!srcWallet.equals(other.srcWallet))
			return false;
		if (timestamp != other.timestamp)
			return false;
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", srcWallet=" + srcWallet + ", dstWallet=" + dstWallet + ", value=" + value
				+ ", hash=" + hash + ", previousHash=" + previousHash + ", timestamp=" + timestamp + "]";
	}

}
