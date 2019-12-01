package g3.com.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import g3.com.entity.Member;
import g3.com.repository.MemberRepository;
import g3.com.repository.UserRolesRepository;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	private final MemberRepository userRepository;
	private final UserRolesRepository userRolesRepository;

	@Autowired
	public CustomUserDetailsService(MemberRepository userRepository, UserRolesRepository userRolesRepository) {
		this.userRepository = userRepository;
		this.userRolesRepository = userRolesRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member user = userRepository.findByEmail(email);
		if (null == user) {
			throw new UsernameNotFoundException("No user present with username: " + email);
		} else {
			List<String> userRoles = userRolesRepository.findRoleByEmail(email);
			return new CustomUserDetails(user, userRoles);
		}
	}
}
