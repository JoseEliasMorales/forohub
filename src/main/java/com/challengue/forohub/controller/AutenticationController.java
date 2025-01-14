package com.challengue.forohub.controller;


import com.challengue.forohub.domain.usuario.*;
import com.challengue.forohub.infra.security.DatosJWTTOken;
import com.challengue.forohub.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody DatosAutenticacionUsuario datos){
        Authentication authToken=new UsernamePasswordAuthenticationToken(datos.correoElectronico(), datos.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var jwtToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        System.out.println(jwtToken);
        return ResponseEntity.ok(new DatosJWTTOken(jwtToken));
    }

}
