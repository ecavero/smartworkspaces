package pe.isil.smartworkspaces.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfiguracionSeguridad {

   @Bean
   public SecurityFilterChain configure(HttpSecurity http) {
      return http
         .authorizeHttpRequests( auth -> 
               auth
               .anyRequest()
               .permitAll()
         )
         .build();
   }
}
