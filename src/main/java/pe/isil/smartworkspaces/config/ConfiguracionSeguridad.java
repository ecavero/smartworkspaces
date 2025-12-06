package pe.isil.smartworkspaces.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import pe.isil.smartworkspaces.security.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @SneakyThrows
   @Bean
   public SecurityFilterChain configure(HttpSecurity http) {
       return  http
               .formLogin(form -> form.loginPage("/login").permitAll())
               .authorizeHttpRequests((authz) ->authz
                       .requestMatchers("/admin/**").hasRole("ADMIN")
                       .requestMatchers("/usuarios/**").authenticated()
                       .anyRequest().permitAll()
               )

               .userDetailsService(userDetailsServiceImp)
               .logout(
                       logout->logout
                               .logoutUrl("/logout")
                               .logoutSuccessUrl("/")
               )
               .exceptionHandling(
                       customizer->customizer.accessDeniedHandler(accessDeniedHandler())
               )
               .build()
       ;


   }
    @Bean
    AccessDeniedHandler accessDeniedHandler(){
        return (
                (((request, response,
                   accessDeniedException) -> response.sendRedirect(request.getContextPath() + "/403")))
        );
    }
}
