package challlenge.forohub.domain.topico;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoDTO(
        @NotBlank
         String tituloTopico,
        @NotBlank
         String mensajeTopico,
         @NotNull
         LocalDateTime fechaCreacion,
         @NotBlank
         String cursoNombre,
         @NotBlank
         String idUsuario
) {
}
