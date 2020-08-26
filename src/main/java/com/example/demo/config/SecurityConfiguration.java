package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.PersonService;

@Configuration
@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	//https://stackoverflow.com/questions/52143913/authentication-using-cookies-in-spring-boot

	private final PersonService personService;
	
	@Autowired
	public SecurityConfiguration(PersonService personService) {
		this.personService = personService;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(personService);
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
						.antMatchers("/", "/submit", "/register").permitAll()
						.antMatchers("/admin").hasAuthority("ROLE_ADMIN")
						.and()
						.formLogin().loginPage("/login")
						.permitAll()
						.usernameParameter("username")
						.passwordParameter("password")
						.defaultSuccessUrl("/admin")
						.and()
						.csrf().disable(); //TODO remove
		System.out.println("configuring http");
						
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("getting encoder");
		return new BCryptPasswordEncoder(10);
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		System.out.println("getting person service");
		return personService; 
	}
	

}
