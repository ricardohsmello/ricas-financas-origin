package br.com.ricasfinancas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ricasfinancas.model.Role;
import br.com.ricasfinancas.repository.RoleUserRepository;

/**
 * @author ricardo.mello
 *
 */
@Component	
public class RoleUserService {
 
	@Autowired
	private RoleUserRepository roleUserRepository;

	public Role save(Role role) {
		return roleUserRepository.saveAndFlush(role);
	}
	
}
