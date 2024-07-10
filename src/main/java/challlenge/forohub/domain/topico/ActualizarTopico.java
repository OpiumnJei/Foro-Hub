package challlenge.forohub.domain.topico;

import java.time.LocalDateTime;

public record ActualizarTopico(
         Long id,
         String tituloTopico,
         String mensajeTopico,
         LocalDateTime fechaCreacion,
         String cursoNombre,
         String idUsuario
) {
}
