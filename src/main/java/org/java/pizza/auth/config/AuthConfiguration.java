package org.java.pizza.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfiguration {

		@Bean
		PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Bean
		SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		    
			return 
				http.authorizeHttpRequests(a -> a
				        .requestMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
				        .requestMatchers("/pizza/**").hasAnyAuthority("USER", "ADMIN")
				        .requestMatchers("/admin/**").hasAuthority("ADMIN")
				        .requestMatchers("/offer/**").hasAuthority("ADMIN")
				        .requestMatchers("/ingredients/**").hasAuthority("ADMIN")
				        .requestMatchers("/**").permitAll()
				).formLogin(f -> f
					.defaultSuccessUrl("/user")
					.permitAll()
				).logout(l -> l.logoutSuccessUrl("/")
				).build();
		}
}
