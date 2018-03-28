package br.com.marcellopassos.partycoin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcellopassos.partycoin.entities.ApplicationUser;
import br.com.marcellopassos.partycoin.exceptions.UserNotFoundException;
import br.com.marcellopassos.partycoin.services.UserService;
import br.com.marcellopassos.partycoin.vo.SignUpResult;

@RestController
@RequestMapping(value = { "/users" }, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/sign-up")
	public SignUpResult signUp(@RequestBody ApplicationUser user) {
		return this.userService.signUp(user);
	}
	
	@GetMapping(path = "/my-data")
	public ApplicationUser myData() throws UserNotFoundException {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return this.userService.getUserByUsername(username);
	}

}
