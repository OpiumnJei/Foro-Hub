package challlenge.forohub.infra.seguridad;

import challlenge.forohub.domain.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroSeguridad extends OncePerRequestFilter {

    @Autowired
    private GestionarTokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       //Obtiene el token del header de nuestra api
        var headerAuthoritation = request.getHeader("Authorization");
        if (headerAuthoritation != null){
            var token = headerAuthoritation.replace("Bearer ", "");
            var nombreUsuario = tokenService.getSubject(token);
//            System.out.println(tokenService.getSubject(token));
            if(nombreUsuario != null){
                var usuarioValido = userRepository.findBylogin(nombreUsuario);
                var autenticacionUsuario = new UsernamePasswordAuthenticationToken(usuarioValido, null,
                        usuarioValido.getAuthorities()); //forzar inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(autenticacionUsuario);
            }
        }
        filterChain.doFilter(request, response);
    }
}

