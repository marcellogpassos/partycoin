package br.com.marcellopassos.partycoin.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcellopassos.partycoin.entities.ApplicationUser;
import br.com.marcellopassos.partycoin.entities.Transaction;
import br.com.marcellopassos.partycoin.entities.Wallet;
import br.com.marcellopassos.partycoin.exceptions.InsufficientFundsException;
import br.com.marcellopassos.partycoin.exceptions.NegativeValueException;
import br.com.marcellopassos.partycoin.exceptions.NotAuthorizedException;
import br.com.marcellopassos.partycoin.exceptions.SourceDestinationSameException;
import br.com.marcellopassos.partycoin.repositories.BlockchainRepository;
import br.com.marcellopassos.partycoin.repositories.WalletRepository;
import br.com.marcellopassos.partycoin.vo.Balance;
import br.com.marcellopassos.partycoin.vo.SendResult;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private BlockchainRepository blockchainRepository;
	@Autowired
	private WalletRepository walletRepository;

	private static int BALANCE = 1;
	private static int TRANSACTIONS = 2;
	private static int SEND = 3;

	@Override
	public Balance balance(String walletHash, String key) throws NotAuthorizedException {
		Wallet wallet = this.getWallet(walletHash);
		this.validateWalletOwnership(WalletServiceImpl.BALANCE, wallet, key);
		Float balance = this.blockchainRepository.getBalance(wallet.getHash());
		if (balance == null)
			balance = 0f;
		return new Balance(wallet.getHash(), balance);
	}

	@Override
	public Collection<Transaction> transactions(String walletHash, String key) throws NotAuthorizedException {
		Wallet wallet = this.getWallet(walletHash);
		this.validateWalletOwnership(WalletServiceImpl.TRANSACTIONS, wallet, key);
		return this.blockchainRepository.listTransactionsByWallet(wallet.getHash());
	}

	@Override
	public SendResult send(String srcWalletHash, String dstWalletHash, float amount, String key)
			throws NotAuthorizedException, NegativeValueException, InsufficientFundsException,
			SourceDestinationSameException {
		Wallet srcWallet = this.getWallet(srcWalletHash);
		this.validateWalletOwnership(WalletServiceImpl.SEND, srcWallet, key);
		Wallet dstWallet = this.getWallet(dstWalletHash);

		System.out.println("sending:" + srcWallet.getHash() + ":" + dstWallet.getHash() + ":" + amount);
		String transactionHash = this.blockchainRepository.send(srcWallet.getHash(), dstWallet.getHash(), amount);
		this.validateTransactionHash(transactionHash);
		System.out.println(
				"sent:" + srcWallet.getHash() + ":" + dstWallet.getHash() + ":" + amount + ":" + transactionHash);

		return new SendResult(srcWallet.getHash(), dstWallet.getHash(), amount, transactionHash);
	}

	@Override
	public Collection<Wallet> getUserWallets(ApplicationUser user) {
		return this.walletRepository.findByUserId(user.getId());
	}

	private Wallet getWallet(String walletHash) {
		return this.walletRepository.findById(walletHash).get();
	}

	private boolean validateWalletOwnership(int operation, Wallet wallet, String key) throws NotAuthorizedException {
		if ((operation == WalletServiceImpl.BALANCE)
				|| (operation == WalletServiceImpl.TRANSACTIONS) && (wallet.getUserId() == null))
			return true;
		if (wallet != null)
			return true;
		else
			throw new NotAuthorizedException();
	}

	private boolean validateTransactionHash(String transactionHash)
			throws NegativeValueException, InsufficientFundsException, SourceDestinationSameException {
		if (transactionHash.equals("-1"))
			throw new NegativeValueException();
		else if (transactionHash.equals("-2"))
			throw new InsufficientFundsException();
		else if (transactionHash.equals("-3"))
			throw new SourceDestinationSameException();
		else
			return true;
	}

}
