package br.com.ricasfinancas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ricasfinancas.model.User;
import br.com.ricasfinancas.repository.UserRepository;

/**
 * @author ricardo.mello
 *
 */
@Component	
public class UserService {
 
	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> findByUserName(String username) {
		return userRepository.findByUserName(username);
	}

	public User save(User user) {
		return userRepository.saveAndFlush(user);
	}
	
}
