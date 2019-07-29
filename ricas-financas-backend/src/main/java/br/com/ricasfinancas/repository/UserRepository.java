package br.com.ricasfinancas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ricasfinancas.model.User;

/**
 * @author ricardo.mello
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "select u.*, ur.* authority from users u, role_users ur where u.username=? and u.iduser = ur.iduser", nativeQuery = true)
	public Optional<User> findByUserName(@Param("username") String username);

}
