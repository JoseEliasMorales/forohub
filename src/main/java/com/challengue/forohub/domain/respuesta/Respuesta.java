package com.challengue.forohub.domain.respuesta;


import com.challengue.forohub.domain.topico.Topico;
import com.challengue.forohub.domain.usuario.DatosUsuario;
import com.challengue.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
    private LocalDateTime fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private Boolean solucion;


}
