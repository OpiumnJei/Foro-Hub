package challlenge.forohub.domain.topico;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tituloTopico;
    private String mensajeTopico;
    private LocalDateTime fechaCreacion;
    private String cursoNombre;
    private String idUsuario;

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getTituloTopico() {
        return tituloTopico;
    }

    public String getMensajeTopico() {
        return mensajeTopico;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public String getCursoNombre() {
        return cursoNombre;
    }

    public Long getId() {
        return id;
    }

    public Topico(TopicoDTO topicoDTO) {
        this.tituloTopico = topicoDTO.tituloTopico();
        this.mensajeTopico = topicoDTO.mensajeTopico();
        this.fechaCreacion = topicoDTO.fechaCreacion();
        this.cursoNombre = topicoDTO.cursoNombre();
        this.idUsuario = topicoDTO.idUsuario();
    }

    public void actualizarDatosTopico(ActualizarTopico actualizarTopico) {
        if(actualizarTopico.tituloTopico() != null){
            this.tituloTopico = actualizarTopico.tituloTopico();
        }
        if(actualizarTopico.mensajeTopico() != null){
            this.mensajeTopico = actualizarTopico.mensajeTopico();
        }
        if(actualizarTopico.fechaCreacion() != null){
            this.fechaCreacion = actualizarTopico.fechaCreacion();
        }
        if(actualizarTopico.cursoNombre() != null){
            this.cursoNombre = actualizarTopico.cursoNombre();
        }
        if(actualizarTopico.idUsuario() != null){
            this.idUsuario = actualizarTopico.idUsuario();
        }
    }
}
