package com.marcos.foro.controller;

import com.marcos.foro.models.topicos.DatosListaTopicos;
import com.marcos.foro.repositories.CursoRepository;
import com.marcos.foro.repositories.TopicoRepository;
import com.marcos.foro.models.topicos.DatosDetalleTopico;
import com.marcos.foro.models.topicos.DatosRegistroTopico;
import com.marcos.foro.models.topicos.Topico;
import com.marcos.foro.repositories.UsuarioRepository;
import jakarta.validation.Valid;
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
        var topics = topicoRepository.findAll(pageable)
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

}
