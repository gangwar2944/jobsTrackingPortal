package com.jobs.jobstrackingportal.Config;

import com.jobs.jobstrackingportal.Repository.UserRepository;
import com.jobs.jobstrackingportal.Security.CustomUserDetailService;
import com.jobs.jobstrackingportal.Security.JwtAuthenticationEntryPoint;
import com.jobs.jobstrackingportal.Security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/api/v1/auth/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin();

		return httpSecurity.build();
	}

	@Bean
	public UserDetailsService userDetailsService(){

		return new CustomUserDetailService();
	}

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.build();
	}
	

}
