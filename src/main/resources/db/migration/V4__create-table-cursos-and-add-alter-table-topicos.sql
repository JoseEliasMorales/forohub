create table cursos(
    id bigint not null auto_increment,
    nombre varchar(100) not null unique,
    categoria varchar(100) not null,
    primary key(id)
);

alter table topicos
add constraint fk_topicos_curso_id foreign key(curso_id) references cursos(id);