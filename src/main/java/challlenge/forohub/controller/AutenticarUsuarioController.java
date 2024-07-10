package challlenge.forohub.controller;

import challlenge.forohub.domain.user.User;
import challlenge.forohub.domain.user.UserDTO;
import challlenge.forohub.infra.seguridad.GestionarTokenService;
import challlenge.forohub.infra.seguridad.TokenGeneradoDTO;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/login")
public class AutenticarUsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private GestionarTokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid UserDTO userDTO){

        //autenticamos los datos del usuario DTO con su login = usuario y la contrasenia
        //generamos un token
        Authentication tokenAutenticacion = new UsernamePasswordAuthenticationToken(userDTO.login(), userDTO.contrasenia());

        //autentica el token generado
        var usuarioAutenticado = authenticationManager.authenticate(tokenAutenticacion);
        var tokenJWT = tokenService.generarTokenJWT((User) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new TokenGeneradoDTO(tokenJWT));
    }
}
