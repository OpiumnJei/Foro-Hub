package challlenge.forohub.infra.seguridad;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {

    @Autowired
    private FiltroSeguridad filtroSeguridad;

    //Metodo para sobreescribir los valores/comportamientos de autentificacion que trae spring security por default
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()  // Desactiva la protección CSRF
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Indicamos el tipo de sesion a spring, sin estado
                .and().authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/login")
                .permitAll() //abrir login para todo path que empiece con /login
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(filtroSeguridad, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    //metodo para que el AuthenticationManager se inyecte correctamente en la clase AutenticaciorUsuarioController
    //se marca con @Bean para que spring tenga el metodo bajo su contexto (Lo gestione)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //metodo para generar la encriptacion usando Bcrypt
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
