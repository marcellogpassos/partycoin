package br.com.marcellopassos.partycoin.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import br.com.marcellopassos.partycoin.entities.Wallet;

public interface WalletRepository extends CrudRepository<Wallet, String> {
	
	public Collection<Wallet> findByUserId(Long userId);

}
