package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	protected void canfigure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
			.antMatchers("/", "/shop/**", "/register", "/h2-console/**").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.failureUrl("/login?error=true")
			.defaultSuccessUrl("/")
			.usernameParameter("email")
			.passwordParameter("password")
			.and()
			.oauth2Login()
			.loginPage("/login")
			.successHandler(null)//googleOAuth2SuccessHandler
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.and()
			.exceptionHandling()
			.and()
			.csrf()
			.disable();
		
		
		http.headers().frameOptions().disable();
	}
	
	
	@Bean
	public BCryptPasswordEncoder bcpe{
		return new BCryptPasswordEncoder();
	}
	
	protected void confrigure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(customUserDetailService);
	}
	
	protected void confrigure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/resources/**", "/static/**", "/images/**", "/productimages/**", "/css/**", "/js/**");
	}
	
	
	
	
	
	
	
}