package br.com.marcellopassos.partycoin.services;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.marcellopassos.partycoin.entities.ApplicationUser;
import br.com.marcellopassos.partycoin.exceptions.UserNotFoundException;
import br.com.marcellopassos.partycoin.repositories.UserRepository;
import br.com.marcellopassos.partycoin.vo.SignUpResult;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser user = userRepository.findByUsername(username);
		if (user == null) 
			throw new UsernameNotFoundException(username);
		return new User(user.getUsername(), user.getPassword(), emptyList());
	}
	
	@Override
	public SignUpResult signUp(ApplicationUser user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		this.userRepository.save(user);
		return new SignUpResult(user.getUsername());
	}

	@Override
	public ApplicationUser getUserByUsername(String username) throws UserNotFoundException {
		ApplicationUser user = userRepository.findByUsername(username);
		if (user == null) 
			throw new UserNotFoundException(username);
		return user;
	}

}
