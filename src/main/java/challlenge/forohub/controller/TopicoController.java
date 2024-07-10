package challlenge.forohub.controller;

import challlenge.forohub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    //CREAR UN TOPICO
    @PostMapping
    public ResponseEntity<RespuestaTopicoDTO> crearTopico(@RequestBody @Valid TopicoDTO topicoDTO, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicoRepository.save(new Topico(topicoDTO));
        RespuestaTopicoDTO respuestaTopicoDTO = new RespuestaTopicoDTO
                        (topico.getId(),
                        topico.getTituloTopico(),
                        topico.getMensajeTopico(),
                        topico.getFechaCreacion(),
                        topico.getCursoNombre(),
                        topico.getIdUsuario());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaTopicoDTO);
    }

    //LISTAR TODOS LOS TOPICOS
    @GetMapping
    public ResponseEntity<Page<ListaTopico>> listarTopicos(Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(ListaTopico::new));

    }

    //LISTAR TOPICO EN ESPECIFICO
    @GetMapping("/{id}")
    public ResponseEntity<RespuestaTopicoDTO> listarTopicoEspefico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new RespuestaTopicoDTO
                (topico.getId(),
                        topico.getTituloTopico(),
                        topico.getMensajeTopico(),
                        topico.getFechaCreacion(),
                        topico.getCursoNombre(),
                        topico.getIdUsuario());

        return ResponseEntity.ok(datosTopico);

    }

    //ACTUALIZAR UN TOPICO EN ESPEFICO
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaTopicoDTO> actualizarTopico(@PathVariable Long id,@RequestBody @Valid ActualizarTopico actualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(id);
            topico.actualizarDatosTopico(actualizarTopico);

        var datosTopico = new RespuestaTopicoDTO
                (topico.getId(),
                        topico.getTituloTopico(),
                        topico.getMensajeTopico(),
                        topico.getFechaCreacion(),
                        topico.getCursoNombre(),
                        topico.getIdUsuario());

        return ResponseEntity.ok(datosTopico);
    }

    //ELIMINAR UN TOPICO EN ESPEFICO

    //este metodo seria la manera correcta de eliminar un topico
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity eliminarTopico(@PathVariable Long id){
//        Topico topico = topicoRepository.getReferenceById(id);
//            topicoRepository.delete(topico);
//        return ResponseEntity.noContent().build();
//    }

    //esta version del metodo funciona, pero no es la forma mas adecuada
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }

}
