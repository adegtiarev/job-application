package kg.aios.application.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class RestUser extends User {

	private static final long serialVersionUID = 6333302671219505416L;

	private final Long id;

	public RestUser(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
