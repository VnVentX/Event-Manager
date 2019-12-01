package g3.com.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import g3.com.entity.Member;

public class CustomUserDetails extends Member implements UserDetails {
	private static final long serialVersionUID = 1L;
	private List<String> userRoles;

	public CustomUserDetails(Member user, List<String> userRoles) {
		super(user);
		this.userRoles = userRoles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.getEnabled() == 1 ? true : false;
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public String getEmail() {
		return super.getEmail();
	}

	public String getFullName() {
		return super.getFirstName() + " " + super.getLastName();
	}

	public Member getUser() {
		return this;
	}
}