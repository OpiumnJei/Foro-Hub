package challlenge.forohub.domain.topico;

import java.time.LocalDateTime;

public record ListaTopico(Long id, String tituloTopico, String mensajeTopico, LocalDateTime fechaCreacion, String cursoNombre, String idUsuario)
{
    public ListaTopico(Topico topico){
        this(topico.getId(), topico.getTituloTopico(), topico.getMensajeTopico(), topico.getFechaCreacion(), topico.getCursoNombre(), topico.getIdUsuario());
    }
}
