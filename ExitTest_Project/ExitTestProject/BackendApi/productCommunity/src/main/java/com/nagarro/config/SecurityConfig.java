package com.nagarro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import com.nagarro.service.CustomerUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

@Autowired
private CustomerUserDetailsService customerUserDetailsService;

@Autowired
private JwtRequestFilter jwtFilter;
/*
 * This method used for authentication and also encode the password
 * @Param AuthenticationMangerBuilder
 * @Return nothing
 */
 @Override
 protected void configure(AuthenticationManagerBuilder auth) throws Exception{
  auth.userDetailsService(customerUserDetailsService).passwordEncoder(passwordEncoder());;
 }
 /*
  * This method is used for configure security
  * @Param HttpSecurity
  * @Return nothing
  */
 @Override
 protected void configure(HttpSecurity http) throws Exception{
	http.cors();
	http
      .csrf()  
      .disable()    
      .authorizeRequests()
      .antMatchers("/auth").permitAll()
      .antMatchers("/getstats").permitAll()
      .antMatchers("/registerUser").permitAll()
      .anyRequest().authenticated()
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  http.headers()
  .addHeaderWriter(
          new StaticHeadersWriter("Access-Control-Allow-Origin", "*")
  );

  http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  
 }

 @Bean
 @Override
 public AuthenticationManager authenticationManagerBean() throws Exception {
  return super.authenticationManagerBean();
 }
 
 @Bean
 public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
 }
 
}