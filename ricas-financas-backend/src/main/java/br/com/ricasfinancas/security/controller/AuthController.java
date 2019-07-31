package br.com.ricasfinancas.security.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ricasfinancas.security.dto.JwtAuthenticationDTO;
import br.com.ricasfinancas.security.dto.TokenDTO;
import br.com.ricasfinancas.security.response.Response;
import br.com.ricasfinancas.security.utils.JwtTokenUtil;
import br.com.ricasfinancas.swaggerconfig.ControllerDocumentation;
import io.swagger.annotations.Api;


/**
 * @author ricardo.mello
 *
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@ControllerDocumentation
@Api(value="authentication-context", description="Rest API for working in the authentication context")
public class AuthController {
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	private static final String TOKEN_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostMapping
	public ResponseEntity<Response<TokenDTO>> generateTokenJwt(
			@Valid @RequestBody JwtAuthenticationDTO authenticationDTO, BindingResult result)
			throws AuthenticationException {
		Response<TokenDTO> response = new Response<>();

		if (result.hasErrors()) {
			log.error("Err validating launch: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		log.info("Generating token for username {}.", authenticationDTO.getUsername());
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationDTO.getUsername(), authenticationDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.getUsername());
		String token = jwtTokenUtil.obterToken(userDetails);
		response.setData(new TokenDTO(token));

		return ResponseEntity.ok(response);
	}

	
	@PostMapping(value = "/refresh")
	public ResponseEntity<Response<TokenDTO>> refreshTokenJwt(HttpServletRequest request) {
		log.info("Generating refresh token JWT.");
		Response<TokenDTO> response = new Response<>();
		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));
		
		if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
			token = Optional.of(token.get().substring(7));
        }
		
		if (!token.isPresent()) {
			response.getErrors().add("Token not informedo.");
		} else if (!jwtTokenUtil.tokenValido(token.get())) {
			response.getErrors().add("Invalid token or expired.");
		}
		
		if (!response.getErrors().isEmpty()) { 
			return ResponseEntity.badRequest().body(response);
		}
		
		String refreshedToken = jwtTokenUtil.refreshToken(token.get());
		response.setData(new TokenDTO(refreshedToken));
		return ResponseEntity.ok(response);
	}
}
