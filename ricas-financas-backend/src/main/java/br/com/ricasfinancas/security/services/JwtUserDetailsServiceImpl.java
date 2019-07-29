package br.com.ricasfinancas.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ricasfinancas.model.User;
import br.com.ricasfinancas.service.UserService;

/**
 * @author ricardo.mello
 *
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userService.findByUserName(username);

		if (user.isPresent()) {
			return user.get();
		}

		throw new UsernameNotFoundException("Username not foud.");
	}
	

}