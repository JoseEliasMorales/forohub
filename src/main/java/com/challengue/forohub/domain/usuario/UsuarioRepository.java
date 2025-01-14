package com.challengue.forohub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByCorreoElectronico(String correoElectronico);

    @Query("""
            SELECT u FROM Usuario u
            WHERE u.correoElectronico = :email
            """)
    Usuario findByEmail(String email);
}
