package br.com.marcellopassos.partycoin.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.marcellopassos.partycoin.entities.Transaction;

public interface BlockchainRepository extends CrudRepository<Transaction, Long> {

	@Query(value = ""
			+ "SELECT "
				+ "SUM( IF( bloc.src_wallet = ?1, bloc.value * (-1), bloc.value ) ) balance "
			+ "FROM "
				+ "blockchain bloc "
			+ "WHERE "
				+ "(bloc.src_wallet = ?1) OR (bloc.dst_wallet = ?1)"
			+ "", nativeQuery = true)
	public float getBalance(String walletHash);
	
	@Query(value = ""
			+ "SELECT "
				+ "bloc.* "
			+ "FROM "
				+ "blockchain bloc "
			+ "WHERE "
				+ "(bloc.src_wallet = ?1) OR (bloc.dst_wallet = ?1) "
			+ "ORDER BY "
				+ "bloc.timestamp"
			+ "", nativeQuery = true)
	public Collection<Transaction> listTransactionsByWallet(String walletHash);
	
	@Query(value = "SELECT SEND(?1, ?2, ?3)", nativeQuery = true)
	public String send(String srcWalletHash, String dstWalletHash, float amount);

}
