package br.com.ricasfinancas.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ricasfinancas.model.Role;
import br.com.ricasfinancas.model.User;
import br.com.ricasfinancas.security.response.Response;
import br.com.ricasfinancas.service.RoleUserService;
import br.com.ricasfinancas.service.UserService;
import br.com.ricasfinancas.util.RoleType;

/**
 * @author ricardo.mello
 *
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RoleUserService roleUserService;

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Response<User>> add(@RequestBody User user, BindingResult result) {
		Response<User> response = new Response<>();
		
		verifyUserExists(user, result);

		if (result.hasErrors()) {
			log.error("Errr while validate data:  {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setEnabled(true);
		user = userService.save(user);

		Role role = new Role();
		role.setRole(RoleType.ROLE_USER.name());
		role.setUsers(user);

		roleUserService.save(role);

		response.setData(user);
		return ResponseEntity.ok(response);

	}

	private void verifyUserExists(User user, BindingResult result) {
		Optional<User> findByUserName = userService.findByUserName(user.getUsername());
		if (findByUserName.isPresent())
			result.addError(new ObjectError("User", "E-mail already exists."));
	}

}
