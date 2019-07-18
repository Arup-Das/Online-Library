package com.example.arup.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.arup.service.UserService;
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserService userService;
	@Autowired
	private DataSource dataSource;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/registration**","/js/**","/css/**","/img/**","/webjars/**","/registration/verify-user**").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login").permitAll()
			.and()
            	.logout()
            		.invalidateHttpSession(true)
            		.clearAuthentication(true)
            		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            		.logoutSuccessUrl("/login?logout")
            	.permitAll()
            .and()
            	.rememberMe()
            		.rememberMeParameter("remember-me")
            		.tokenRepository(tokenRepository()).tokenValiditySeconds(1209600);
		
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}	
	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}
}
