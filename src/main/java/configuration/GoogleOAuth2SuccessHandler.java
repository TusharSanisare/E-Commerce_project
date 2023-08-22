package configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import model.User;
import repository.RoleRepository;
import repository.UserPerository;
import model.Role;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	RoleRepository rr;
	
	@Autowired
	UserPerository ur;
	
	private DefaultRedirectStrategy rs = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
		String email = token.getPrincipal().getAttributes().get("email").toString();
		if(ur.findUserByEmail(email).isPresent()) {
			
		}else {
			User user = new User();
			user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
			user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
			user.setEmail(email);
			List<Role> roles = new ArrayList<>();
			roles.add(rr.findById(2).get());
			user.setRoles(roles);
			ur.save(user);
			
		}
		rs.sendRedirect(request, response, "/");
	}
	
	
}
