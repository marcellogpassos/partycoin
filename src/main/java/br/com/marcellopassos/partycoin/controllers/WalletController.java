package br.com.marcellopassos.partycoin.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcellopassos.partycoin.entities.ApplicationUser;
import br.com.marcellopassos.partycoin.entities.Transaction;
import br.com.marcellopassos.partycoin.entities.Wallet;
import br.com.marcellopassos.partycoin.exceptions.InsufficientFundsException;
import br.com.marcellopassos.partycoin.exceptions.NegativeValueException;
import br.com.marcellopassos.partycoin.exceptions.NotAuthorizedException;
import br.com.marcellopassos.partycoin.exceptions.SourceDestinationSameException;
import br.com.marcellopassos.partycoin.exceptions.UserNotFoundException;
import br.com.marcellopassos.partycoin.services.UserService;
import br.com.marcellopassos.partycoin.services.WalletService;
import br.com.marcellopassos.partycoin.vo.Balance;
import br.com.marcellopassos.partycoin.vo.SendResult;

@RestController
@RequestMapping(value = { "/wallets" }, produces = MediaType.APPLICATION_JSON_VALUE)
public class WalletController {

	@Autowired
	private WalletService walletService;
	@Autowired
	private UserService userService;

	@GetMapping(path = "")
	public Collection<Wallet> getUserWallets() throws UserNotFoundException {
		return this.walletService.getUserWallets(this.getUser());
	}

	@GetMapping(path = "/{walletHash}/balance")
	public Balance balance(@PathVariable String walletHash) throws NotAuthorizedException, UserNotFoundException {
		return this.walletService.balance(walletHash, this.getUser());
	}

	@GetMapping(path = "/{walletHash}/transactions")
	public Collection<Transaction> transactions(@PathVariable String walletHash)
			throws NotAuthorizedException, UserNotFoundException {
		return this.walletService.transactions(walletHash, this.getUser());
	}

	@PostMapping(path = "/{walletHash}/send")
	public SendResult send(@PathVariable String walletHash, @RequestParam String dstWalletHash,
			@RequestParam float amount) throws NotAuthorizedException, NegativeValueException,
			InsufficientFundsException, SourceDestinationSameException, UserNotFoundException {
		return this.walletService.send(walletHash, dstWalletHash, amount, this.getUser());
	}

	private ApplicationUser getUser() throws UserNotFoundException {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return this.userService.getUserByUsername(username);
	}

}