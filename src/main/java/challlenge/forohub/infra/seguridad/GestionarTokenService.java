package challlenge.forohub.infra.seguridad;

import challlenge.forohub.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class GestionarTokenService {

    @Value("${challenge.forohub.secret}")
    private String secretCode;

    //Generar un token
    public String generarTokenJWT(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretCode); //codigo para validar la firma del token
            return JWT.create()
                    .withIssuer("Foro Hub")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generarTiempoExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }

    }

    //Verificar si el usuario es valido, y retornar el subject(nombre del usuario)
    public String getSubject(String token) {
        if(token == null){
            throw new RuntimeException("");
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretCode); //validar firma
            verifier = JWT.require(algorithm)
                    .withIssuer("Foro Hub")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }


  //Metodo para generar la duracion que tendra el toquen como valido, pasado ese tiempo se considerara invalido
    private Instant generarTiempoExpiracion () {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-04:00"));
    }
}
