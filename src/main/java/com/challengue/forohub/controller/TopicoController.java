package com.challengue.forohub.controller;


import com.challengue.forohub.domain.curso.DatosCurso;
import com.challengue.forohub.domain.respuesta.DatosRespuestaCreada;
import com.challengue.forohub.domain.respuesta.ResponderTopico;
import com.challengue.forohub.domain.topico.*;
import com.challengue.forohub.domain.usuario.DatosUsuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private CreacionDeTopico creacionDeTopico;

    @Autowired
    private ResponderTopico responderTopico;

    @PostMapping
    public ResponseEntity<DatosTopicoCreado> crearTopico(@RequestBody @Valid DatosCreacionTopico datos, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = creacionDeTopico.crearTopico(datos);
        DatosTopicoCreado datosTopicoCreado = transformarDatos(topico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosTopicoCreado);
    }

    @GetMapping
    public Page<DatosListadoTopicos> verListadoTopicos(@PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion){
        return repository.findAll(paginacion).map(DatosListadoTopicos::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopicos> verTopico(@PathVariable Long id){
        var topicoBuscado = repository.getReferenceById(id);
        DatosListadoTopicos datosListadoTopicos = new DatosListadoTopicos(topicoBuscado);
        return ResponseEntity.ok(datosListadoTopicos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosTopicoCreado> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datos){
        var topico = repository.findById(datos.id());
        if(topico.isPresent()){
            topico.get().actualizarTopico(datos);
            DatosTopicoCreado datosTopicoCreado = transformarDatos(topico.get());
            return ResponseEntity.ok(datosTopicoCreado);
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        var topicoAEliminar = repository.findById(id);
        if (topicoAEliminar.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.accepted().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<DatosRespuestaCreada> anadirRespuesta(@RequestBody @Valid DatosRespuestaCreada datosRespuestaCreada, UriComponentsBuilder uriComponentsBuilder){
        var topicoRespondido = repository.findById(datosRespuestaCreada.topicoId());
        if(topicoRespondido.isPresent()){
            responderTopico.responder(datosRespuestaCreada);
            URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoRespondido.get().getId()).toUri();
            return ResponseEntity.created(url).body(datosRespuestaCreada);
        }
        return ResponseEntity.notFound().build();
    }

    private DatosTopicoCreado transformarDatos(Topico topico) {
        DatosTopicoCreado datosTopicoCreado = new DatosTopicoCreado(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                new DatosUsuario(
                        topico.getAutor().getNombre(),
                        topico.getAutor().getCorreoElectronico()
                ),
                new DatosCurso(
                        topico.getCurso().getNombre(),
                        topico.getCurso().getCategoria()
                )
        );

        return datosTopicoCreado;
    }
}
