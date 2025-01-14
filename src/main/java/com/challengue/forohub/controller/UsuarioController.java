package com.challengue.forohub.controller;

import com.challengue.forohub.domain.usuario.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping("/usuarios")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<List<DatosListadoUsuarios>> listadoUsuarios(){
        var lista = repository.findAll().stream().map(DatosListadoUsuarios::new).toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/register")
    public ResponseEntity<DatosUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder){
        var encriptarContrasena = passwordEncoder.encode(datos.contrasena());
        var nuevoUsuario = new Usuario(null, datos.nombre(), datos.email(), encriptarContrasena);
        repository.save(nuevoUsuario);
        var datosNuevoUsuario = new DatosUsuario(nuevoUsuario.getNombre(), nuevoUsuario.getCorreoElectronico());
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(nuevoUsuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosNuevoUsuario);
    }

    @PutMapping("/usuarios")
    @SecurityRequirement(name = "bearer-key")
    @Transactional
    public ResponseEntity<DatosUsuario> actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario){
        var usuarioAActualizar = repository.findById(datosActualizarUsuario.id());
        if (usuarioAActualizar.isPresent()) {
            var usuarioEncontrado = usuarioAActualizar.get();
            String nuevaContrasena = null;
            if (datosActualizarUsuario.contrasena() != null){
                nuevaContrasena = passwordEncoder.encode(datosActualizarUsuario.contrasena());
            }
            usuarioEncontrado.actualizarUsuario(new DatosActualizarUsuario(usuarioEncontrado.getId(), datosActualizarUsuario.nombre(), datosActualizarUsuario.correoElectronico(), nuevaContrasena));
            repository.save(usuarioEncontrado);
            DatosUsuario datosNuevoUsuario = new DatosUsuario(usuarioEncontrado.getNombre(), usuarioEncontrado.getCorreoElectronico());
            return ResponseEntity.ok(datosNuevoUsuario);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/usuarios/{id}")
    @SecurityRequirement(name = "bearer-key")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        if(usuario != null){
            repository.delete(usuario);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
