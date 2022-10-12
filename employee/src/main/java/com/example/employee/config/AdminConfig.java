package com.example.employee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.employee.jwt.JwtRequestFilter;
import com.example.employee.service.Authencation;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class AdminConfig extends WebSecurityConfigurerAdapter{
	
	
	private final JwtRequestFilter jwtReq;
	private final Authencation authencation;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authencation).passwordEncoder(bCryptPasswordEncoder());
	}

	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors()
		.and()
		.authorizeRequests()
		.antMatchers("/api/employee/login").permitAll()
		.antMatchers("/api/employee/add","/api/employee/update/{id}","/api/employee/addAdmin").hasRole("ADMIN")
		.antMatchers("/api/timeSheet/add","/api/timeSheet/update","/api/timeSheet/getTimeSheet/{id}",
				"/api/timeSheet/getAllTimeSheet/{id}","/api/timeSheet/getTimeSheet/{id}").hasAnyRole("EMPLOYEE","ADMIN")
		.anyRequest().authenticated()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtReq,UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	

	
}
