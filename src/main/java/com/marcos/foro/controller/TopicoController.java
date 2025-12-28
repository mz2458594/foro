package com.marcos.foro.controller;

import com.marcos.foro.models.curso.Curso;
import com.marcos.foro.models.topicos.*;
import com.marcos.foro.models.usuario.Usuario;
import com.marcos.foro.repositories.CursoRepository;
import com.marcos.foro.repositories.TopicoRepository;
import com.marcos.foro.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/foro")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;


    @GetMapping
    public ResponseEntity<Page<DatosListaTopicos>> obtenerTopicos(
            @PageableDefault(size = 10,
            sort = {"fecha"},
            direction = Sort.Direction.ASC
    )Pageable pageable){
        var topics = topicoRepository.findAllByEstadoTrue(pageable)
                .map(DatosListaTopicos::new);

        return ResponseEntity.ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> obtenerTopicoPorId(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }


    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder){
        var usuario = usuarioRepository.getReferenceById(datos.idUsuario());

        var curso = cursoRepository.getReferenceById(datos.idCurso());

        var topico = topicoRepository.save(new Topico(datos, usuario, curso));
        var uri = uriComponentsBuilder.path("/foro/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datos, @PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);

        Usuario autor = null;
        Curso curso = null;

        if (datos.id_autor() != null){
            autor = usuarioRepository.getReferenceById(datos.id_autor());
        }

        if (datos.id_curso() != null){
            curso = cursoRepository.getReferenceById(datos.id_curso());
        }

        topico.actualizar(datos, autor, curso);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        topico.eliminar();
        return ResponseEntity.noContent().build();
    }

}
