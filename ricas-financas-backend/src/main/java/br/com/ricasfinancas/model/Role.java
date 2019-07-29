package br.com.ricasfinancas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ricardo.mello
 *
 */
@Data
@NoArgsConstructor
@Entity(name ="role_users")
public class Role implements GrantedAuthority  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "role_id_seq")
	@SequenceGenerator(name="role_id_seq", sequenceName="role_id_seq", allocationSize=1)
	@Column(name="idrole", updatable=false, nullable=false)
	private Long id;
	
	private String role;
	
	@ManyToOne
	@JoinColumn(name = "iduser")
    private User users;

	@Override
	public String getAuthority() {
		return role;
	}
	

}
