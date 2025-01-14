package com.challengue.forohub.domain.usuario;

public record DatosListadoUsuarios(
        Long id,
        String nombre,
        String correoElectronico

) {
    public DatosListadoUsuarios(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico()
        );
    }
}
