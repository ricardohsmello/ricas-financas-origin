package br.com.ricasfinancas.util;

/**
 * @author ricardo.mello
 *
 */
public enum RoleType {
	
	ROLE_ADMIN(0),
	ROLE_USER(1);
 
    private int role;
 
    RoleType(int role) {
        this.role = role;
    }
 
    public int getRole() {
        return role;
    }
	
}
