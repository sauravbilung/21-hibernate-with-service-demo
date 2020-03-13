package com.psl.sprinboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfigured extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		   
		  /* http.csrf().disable();
		   
		   //All requests send to the Web Server request must be authenticated
	       http.authorizeRequests().anyRequest().authenticated();
	        
	       //Use AuthenticationEntryPoint to authenticate user/password
	       http.httpBasic(); */
		
		 http.httpBasic().and().authorizeRequests()
	        .antMatchers("/api/employees").hasRole("USER")
	        .antMatchers("/api/employees").hasRole("ADMIN")
	        .and()
	        .csrf().disable();
	        
	}	

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> memConfig=auth.inMemoryAuthentication();
		
		UserDetails u1 = User.withUsername("tom").password("tom").roles("USER").build();
	    UserDetails u2 = User.withUsername("jerry").password("jerry").roles("USER").build();
	    
	    memConfig.withUser(u1);
	    memConfig.withUser(u2);*/
		auth.inMemoryAuthentication()
        .withUser("admin")
        .password(passwordEncoder().encode("admin"))
        .roles("USER")
		.and()
	    .withUser("user").password(passwordEncoder().encode("user")).roles("ABC");
	}  
}
