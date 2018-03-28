package br.com.marcellopassos.partycoin.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.marcellopassos.partycoin.entities.ApplicationUser;

public interface UserRepository extends CrudRepository<ApplicationUser, Long> {

	public ApplicationUser findByUsername(String username);

}
