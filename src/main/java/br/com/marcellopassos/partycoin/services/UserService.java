package br.com.marcellopassos.partycoin.services;

import br.com.marcellopassos.partycoin.entities.ApplicationUser;
import br.com.marcellopassos.partycoin.exceptions.UserNotFoundException;
import br.com.marcellopassos.partycoin.vo.SignUpResult;

public interface UserService {

	public SignUpResult signUp(ApplicationUser user);

	public ApplicationUser getUserByUsername(String username) throws UserNotFoundException;

}
