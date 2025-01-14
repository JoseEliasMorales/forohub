alter table topicos
modify column titulo varchar(100) not null unique,
modify column mensaje varchar(1000) not null unique