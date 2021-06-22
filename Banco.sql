CREATE DATABASE clinica;

CREATE TABLE cargo(
    id INTEGER PRIMARY KEY,
    cargo VARCHAR(20) NOT NULL
);

create table hours (
    id serial primary key,
    hour time not null
)

create table agenda(
id serial primary key,        
idmedico integer references funcionario(id),
idpaciente integer references paciente(id),
idhours integer not null unique references hours(id),
date date not null
);

CREATE TABLE funcionario (
    id SERIAL PRIMARY KEY,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    name VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    type INTEGER,
    CONSTRAINT fk_cargo
        FOREIGN KEY(type)
            REFERENCES cargo(id)
);

CREATE TABLE paciente (
    id SERIAL PRIMARY KEY,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    name VARCHAR(20) UNIQUE NOT NULL,
    fone VARCHAR(11) UNIQUE NOT NULL,
    address VARCHAR(20) NOT NULL
);

inset into hours (hour) values (08:00);
inset into hours (hour) values (09:00);
inset into hours (hour) values (10:00);
inset into hours (hour) values (11:00);
inset into hours (hour) values (13:00);
inset into hours (hour) values (14:00);
inset into hours (hour) values (15:00);
inset into hours (hour) values (16:00);
inset into hours (hour) values (17:00);

INSERT INTO cargo (id, cargo) VALUES (1, 'Diretor');
INSERT INTO cargo (id, cargo) VALUES (2, 'Médico');
INSERT INTO cargo (id, cargo) VALUES (3, 'Recepcionista');


INSERT INTO funcionario (cpf, name, password, type) VALUES ('1234','diretor', 'root', 1);


select m.name, p.name, h.hour, date from agenda as a
inner join funcionario as m on m.id=a.idmedico
inner join paciente as p on p.id=a.idpaciente
inner join hours as h on h.id=a.idhours

