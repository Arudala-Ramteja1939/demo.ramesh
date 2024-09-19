package com.example.ramesh.demo.ramesh.SpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint ;

	@Autowired
	private JwtAuthenticationFilter authenticationFilter ;



	//	public SecurityConfig(UserDetailsService customerUserDetailsService) {
	//		super();
	//		this.setUserDetailsService(customerUserDetailsService);
	//	}


	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/api/comments/**" , "/api/auth/**"
				,"/v3/api-docs/**").permitAll())
		.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/swagger-ui/**").permitAll())
		.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults())
		.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(authenticationFilter,UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	//	@Bean
	//	public UserDetailsService details(PasswordEncoder encrypt)
	//	{
	//		UserDetails user1=User.builder()
	//				.username("ramteja")
	//				.password(encrypt.encode("ramteja"))
	//				.roles("USER")
	//				.build();
	//		UserDetails user2=User.builder()
	//				.username("karthik")
	//				.password(encrypt.encode("karthik"))
	//				.roles("ADMIN")
	//				.build();
	//		return new InMemoryUserDetailsManager(user1,user2);
	//
	//	}

	//	@Bean 
	//	public  PasswordEncoder encry()
	//	{
	//		return new BCryptPasswordEncoder();
	//
	//	}

//	@Bean
//	public UserDetailsService userDetailsService() throws Exception{
//		return  new UserInfoUserDetailsService();
//	}
//
//	@Bean
//	public AuthenticationProvider authenticationProvider() throws Exception
//	{
//		DaoAuthenticationProvider  daoAuthenticationProvider=new  DaoAuthenticationProvider();
//		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
//		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//		return daoAuthenticationProvider;
//
//	}
		@Bean
		public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
		{
			return configuration.getAuthenticationManager();
		}

		//	public UserDetailsService getUserDetailsService() {
		//		return userDetailsService;
		//	}
		//
		//	public void setUserDetailsService(UserDetailsService userDetailsService) {
		//		this.userDetailsService = userDetailsService;
		//	}
	}
