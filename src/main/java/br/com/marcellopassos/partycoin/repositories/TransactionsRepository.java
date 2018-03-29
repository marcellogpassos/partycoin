package br.com.marcellopassos.partycoin.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.marcellopassos.partycoin.entities.Transaction;

public interface TransactionsRepository extends CrudRepository<Transaction, Long> {

	@Query(value = ""
			+ "SELECT "
				+ "SUM( CASE WHEN (tran.src_wallet = ?1) THEN (tran.value * (-1)) WHEN (tran.dst_wallet = ?1) THEN (tran.value) ELSE 0 END ) balance "				
			+ "FROM "
				+ "transactions tran "
			+ "WHERE "
				+ "(tran.src_wallet = ?1) OR (tran.dst_wallet = ?1)"
			+ "", nativeQuery = true)
	public Float getBalance(String walletHash);
	
	@Query(value = ""
			+ "SELECT "
				+ "tran.* "
			+ "FROM "
				+ "transactions tran "
			+ "WHERE "
				+ "(tran.src_wallet = ?1) OR (tran.dst_wallet = ?1) "
			+ "ORDER BY "
				+ "tran.created_at DESC"
			+ "", nativeQuery = true)
	public Collection<Transaction> listTransactionsByWallet(String walletHash);
	
	@Query(value = "SELECT SEND(?1, ?2, ?3)", nativeQuery = true)
	public String send(String srcWalletHash, String dstWalletHash, float amount);

}
