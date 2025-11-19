package il.kors.spring_security.config;

import il.kors.spring_security.services.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /*@Bean//Bean annotation means that this method is in the Application Context
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        //We must  encode user password. Otherwise the password will be usual in the DB
        //But all the passwords that we get are encoded
        //Therefore there'll be situation that the DB password and the password that user entered are different
        //password(encoder.encode("admin"))
        UserDetails admin = User.builder().username("admin").password(encoder.encode("admin")).roles("ADMIN").build();
        //UserDetails user = User.builder().username("admin").password(encoder.encode("user")).roles("USER").build();
        //UserDetails dani32 = User.builder().username("admin").password(encoder.encode("password1")).roles("ADMIN", "USER").build();

        //for store and manage all these users in Spring Security there is InMemoryUserDetailsManager class
        return new InMemoryUserDetailsManager(admin*//*,user,dani32*//*);
    }*/

    @Bean//Bean annotation means that this method is in the Application Context
    public UserDetailsService userDetailsService(/*PasswordEncoder encoder*/) {
        return new MyUserDetailsService();
    }

    //prevent csrf attacks
    //"http://localhost:8080/api/v1/apps/welcome" page is allowed for everyone
    //the rest pages only for authorized users
    //get access to the Authorization form for everyone
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("api/v1/apps/welcome", "api/v1/apps/new-user").permitAll()
                        .requestMatchers("api/v1/apps/**").authenticated())
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();
                //http://localhost:8080/api/v1/apps/* - I'm allowed to enter only this address format
                //If I enter for example "//http://localhost:8080/" - I'll get 403 error
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();//BCryptPasswordEncoder -one of the most popular encoders
    }
}
