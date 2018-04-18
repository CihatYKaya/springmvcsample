package bootsample.service;

import bootsample.model.CustomUserDetails;
import bootsample.model.Users;
import bootsample.dao.UsersRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Optional<Users> optionalUsers = usersRepository.findByName(username);
	        
	        optionalUsers
	                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

	        return optionalUsers
	                .map(CustomUserDetails::new).get();
	    }
}
