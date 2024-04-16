package com.secondwind.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.secondwind.entity.Privilege;
import com.secondwind.entity.Role;
import com.secondwind.entity.User;
import com.secondwind.repository.RoleRepository;
import com.secondwind.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<User> optUser = userRepository.findByEmail(email);
		
		if (optUser.isPresent()) {
			User user = optUser.get();
			return new org.springframework.security.core.userdetails.User(
			          user.getEmail(), user.getPassword(), user.isEnabled(), true, true, 
			          true, getAuthorities(user.getRoles()));
		} 
		
		Optional<Role> optRole = roleRepository.findByName("ROLE_USER");
		if (optRole.isPresent()) {
			return new org.springframework.security.core.userdetails.User(
		              " ", " ", true, true, true, true, 
		              getAuthorities(Arrays.asList(optRole.get())));
		}
		
		throw new UsernameNotFoundException("No Role with that name was found");
	}
	
	private List<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}
	
	private List<String> getPrivileges(List<Role> roles) {
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }
	
	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

}
