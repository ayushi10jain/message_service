package com.plivo.message.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;



@Configuration

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Value("${user}")
    private String user;

    @Value("${password}")
    private String pwd;
	
	 private static String REALM="MY_TEST_REALM";
	
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("bill").password("{noop}abc123").roles("ADMIN");
      //  auth.inMemoryAuthentication().withUser("tom").password("{noop}abc123").roles("USER");       
    }
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	  
	      http.csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/message/**").hasRole("ADMIN")
	        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
	        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	      
	    }
	     
	    @Bean
	    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
	        return new CustomBasicAuthenticationEntryPoint();
	    }
	     
	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	    }

}
