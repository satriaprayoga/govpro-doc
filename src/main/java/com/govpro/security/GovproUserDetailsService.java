package com.govpro.security;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.govpro.domain.User;
import com.govpro.repository.UserRepository;

@Component("userDetailsService")
public class GovproUserDetailsService implements UserDetailsService {
	
	
	private final Logger logger=LoggerFactory.getLogger(GovproUserDetailsService.class);
	
	private final UserRepository userRepository;
	
	public GovproUserDetailsService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		logger.debug("Authenticating {}",arg0);
		Optional<User> userFromDatabase=userRepository.findOneWithAuthoritesByLogin(arg0);
		
		
		return userFromDatabase.map(user->{
			List<GrantedAuthority> granted=user.getAuthorities().stream()
					.map(authority->new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
			return new org.springframework.security.core.userdetails.User(arg0.toLowerCase(), user.getPassword(), granted);
		}).orElseThrow(()->new UsernameNotFoundException("user "+arg0+" is not found"));
	}

}
