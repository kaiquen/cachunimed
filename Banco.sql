CREATE DATABASE clinica;

CREATE TABLE cargo(
    id integer PRIMARY KEY,
    cargo varchar(20) NOT NULL
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

INSERT INTO cargo (id, cargo) VALUES (1, 'Diretor');
INSERT INTO cargo (id, cargo) VALUES (2, 'Médico');
INSERT INTO cargo (id, cargo) VALUES (3, 'Recepcionista');

INSERT INTO funcionario (cpf, name, password, type) VALUES ('1234','diretor', 'root', 1);
