package com.marcos.foro.controller;

import com.marcos.foro.models.respuesta.DatosDetalleRespuesta;
import com.marcos.foro.models.respuesta.DatosListaRespuesta;
import com.marcos.foro.models.respuesta.DatosRegistroRespuesta;
import com.marcos.foro.models.respuesta.Respuesta;
import com.marcos.foro.repositories.RespuestaRepository;
import com.marcos.foro.repositories.TopicoRepository;
import com.marcos.foro.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respuesta")
public class RespuestasController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/{id}")
    public ResponseEntity obtenerRespuestaPorId(@PathVariable Long id){
        var respuesta = respuestaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }

    @GetMapping("/topico/{id}")
    public ResponseEntity obtenerRespuestasPorIdTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(topico.getRespuestas().stream().map(DatosListaRespuesta::new));
    }


    @PostMapping
    @Transactional
    public ResponseEntity generarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos,
                                           UriComponentsBuilder uriComponentsBuilder) {
        var topico = topicoRepository.getReferenceById(datos.id_topico());
        var usuario = usuarioRepository.getReferenceById(datos.id_usuario());
        var respuesta = respuestaRepository.save(new Respuesta(datos, topico, usuario));

        var uri =uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(respuesta.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleRespuesta(respuesta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        respuestaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
