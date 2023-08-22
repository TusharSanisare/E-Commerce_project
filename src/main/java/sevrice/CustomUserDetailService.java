package sevrice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import model.CustomUserDetail;
import model.User;
import repository.UserPerository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserPerository ur;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = ur.findUserByEmail(email);
		user.orElseThrow(()-> new UsernameNotFoundException("USER NOT FOUND"));
		
		return user.map(CustomUserDetail::new).get();
	}

}
