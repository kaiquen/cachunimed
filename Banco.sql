CREATE DATABASE clinica;

create table cargo_funcionario(
    id_cargo_funcionario serial,
    nome_cargo varchar(20) unique not null,
        constraint pk_cargo_funcionario
            primary key (id_cargo_funcionario)
);
create table funcionario(
    cod_funcionario serial,
    nome_funcionario varchar(50) not null,
    cpf_funcionario varchar(11) unique not null,
    senha_funcionario varchar(20) not null,
    id_cargo_funcionario integer,
        constraint pk_funcionario
            primary key (cod_funcionario),
        constraint fk_cargo_funcionario
            foreign key (id_cargo_funcionario)
                references cargo_funcionario (id_cargo_funcionario)
);
create table paciente(
    cod_paciente serial,
    nome_paciente varchar(50) not null,
    cpf_paciente varchar(11) unique not null,
    telefone_paciente varchar(11) unique not null,
    endereco_paciente varchar(50),
        constraint pk_paciente
            primary key (cod_paciente) 
);
create table agenda_medico (     
    cod_agenda_medico serial,
    cod_funcionario_medico integer not null,   
    cod_paciente integer not null,
    horario_consulta timestamp unique not null,
        constraint pk_agenda_medico
            primary key (cod_agenda_medico),
        constraint fk_funcionario_medico
            foreign key (cod_funcionario_medico)       
                references funcionario(cod_funcionario),   
        constraint fk_paciente
            foreign key (cod_paciente)
                references paciente(cod_paciente),
        constraint uk_medico_horario 
            unique(cod_funcionario_medico,horario_consulta)
);

INSERT INTO cargo_funcionario (nome_cargo) VALUES ('Diretor');
INSERT INTO cargo_funcionario (nome_cargo) VALUES ('Médico');
INSERT INTO cargo_funcionario (nome_cargo) VALUES ('Recepcionista');

insert into funcionario (nome_funcionario, cpf_funcionario, senha_funcionario, id_cargo_funcionario) values ('Diretor','123','123',1);
