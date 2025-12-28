package com.marcos.foro.controller;

import com.marcos.foro.infra.security.DatosTokenJWT;
import com.marcos.foro.infra.security.TokenService;
import com.marcos.foro.models.usuario.DatosAutenticacion;
import com.marcos.foro.models.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos){

        var autenticationToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.password());
        var autenticacion = authenticationManager.authenticate(autenticationToken);

        var token = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

        return ResponseEntity.ok(
                new DatosTokenJWT(token)
        );
    }

}
