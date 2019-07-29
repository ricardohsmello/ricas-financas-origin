package br.com.ricasfinancas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ricasfinancas.model.Role;

/**
 * @author ricardo.mello
 *
 */
@Repository
public interface RoleUserRepository extends JpaRepository<Role, Long> {
	
}
