package com.example.demo.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	//password encoder reference implemented in WebMvcConfig.
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	//data source implemented out of the box by Spring Boot. 
	//We only need to provide the database information in the application.properties file
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	// Reference to user and role queries stored in application.properties file
	private String usersQuery;
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	//AuthenticationManagerBuilder provides a mechanism to get a user based on the 
	//password encoder, data source, user query and role query.
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			authorizeRequests()
			// Here we define the antMatchers to provide access based on the role(s) 
			//, the parameters for the login process, 
			//the success login page, the failure login page, and the 
			//logout page 
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/lopoc/index").permitAll()
				.antMatchers("/api/**").permitAll()
				.antMatchers("/registration").permitAll()
				.antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
				.authenticated().and().csrf().disable().formLogin()
				.loginPage("/login").failureUrl("/login?error=true")
				.defaultSuccessUrl("/admin/home")
				.usernameParameter("email")
				.passwordParameter("password")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/access-denied");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       // Since we have implemented Spring Security we need to let Spring know
	       //that our resources folder can be served skipping the antMatchers defined
	       .antMatchers("/resources/static/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}