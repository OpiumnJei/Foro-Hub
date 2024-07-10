package challlenge.forohub.domain.topico;


import java.time.LocalDateTime;

public record RespuestaTopicoDTO(Long id,
                                 String tituloTopico,
                                 String mensajeTopico,
                                 LocalDateTime fechaCreacion,
                                 String cursoNombre,
                                 String idUsuario) {


}
