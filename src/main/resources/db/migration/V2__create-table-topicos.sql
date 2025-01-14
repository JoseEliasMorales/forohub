create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(1000) not null,
    fecha_creacion datetime not null,
    status tinyint,
    usuario_id bigint not null,
    curso_id bigint not null,
    primary key (id),

    constraint fk_topicos_usuario_id foreign key(usuario_id) references usuarios(id)
)