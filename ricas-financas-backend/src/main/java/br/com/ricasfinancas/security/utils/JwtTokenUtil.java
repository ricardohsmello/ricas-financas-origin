package br.com.ricasfinancas.security.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.ricasfinancas.model.User;
import br.com.ricasfinancas.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
 

/**
 * @author ricardo.mello
 *
 */
@Component
public class JwtTokenUtil {
	
	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_ROLE = "role";
	static final String CLAIM_KEY_CREATED = "created";
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Autowired
	private UserService userService;
	
	public String getUsernameFromToken(String token) {
		String username;
		try {
			Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}
	
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}
	
	public boolean tokenValido(String token) {
		return !tokenExpirado(token);
	}
	
	private boolean tokenExpirado(String token) {
		Date dataExpiracao = this.getExpirationDateFromToken(token);
		if (dataExpiracao == null) {
			return false;
		}
		return dataExpiracao.before(new Date());
	}
	
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}
	
	public String obterToken(UserDetails userDetails) {
		Optional<User> findByUser = userService.findByUserName(userDetails.getUsername());
		
		if (findByUser.isPresent()) {
			User user = new User();
			
			Map<String, Object> claims = new HashMap<>();
			claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
			userDetails.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));
			claims.put(CLAIM_KEY_CREATED, new Date());
			if (user != null) {
				claims.put("id", user.getIduser());
			}
			
			return gerarToken(claims);
			
		}
		
		return null;
	}
	
	private String gerarToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(gerarDataExpiracao())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	private Date gerarDataExpiracao() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}
	
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = gerarToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}


}
