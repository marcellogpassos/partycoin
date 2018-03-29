package br.com.marcellopassos.partycoin.services;

import java.util.Collection;

import br.com.marcellopassos.partycoin.entities.ApplicationUser;
import br.com.marcellopassos.partycoin.entities.Transaction;
import br.com.marcellopassos.partycoin.entities.Wallet;
import br.com.marcellopassos.partycoin.exceptions.InsufficientFundsException;
import br.com.marcellopassos.partycoin.exceptions.NegativeValueException;
import br.com.marcellopassos.partycoin.exceptions.NotAuthorizedException;
import br.com.marcellopassos.partycoin.exceptions.SourceDestinationSameException;
import br.com.marcellopassos.partycoin.vo.Balance;
import br.com.marcellopassos.partycoin.vo.SendResult;

public interface WalletService {

	public Balance balance(String walletHash, ApplicationUser user) throws NotAuthorizedException;

	public Collection<Transaction> transactions(String walletHash, ApplicationUser user) throws NotAuthorizedException;

	public SendResult send(String srcWalletHash, String dstWalletHash, float amount, ApplicationUser user)
			throws NotAuthorizedException, NegativeValueException, InsufficientFundsException,
			SourceDestinationSameException;

	public Collection<Wallet> getUserWallets(ApplicationUser user);

}
