create database pixelWatchers;

use pixelWatchers;

create table empresa(
	idEmpresa int primary key auto_increment,
    nomeFantasia varchar(100) not null,
    razaoSocial varchar(100) not null,
    CNPJ char (18) unique not null,
    telefone char(15) not null
);

create table arena(
	idArena int primary key auto_increment,
    nomeArena varchar(100) not null,
    cep char(9) not null,
    logradouro varchar(100) not null,
    numero varchar(6) not null,
    bairro varchar(100) not null,
    cidade varchar(100) not null,
    uf char(2) not null,
    fkEmpresa int not null,
    foreign key (fkEmpresa) references empresa (idEmpresa)
);

create table usuario(
	idUsuario int primary key auto_increment,
    nome varchar(100) not null,
    sobrenome varchar(110) not null,
    email varchar(100) unique not null,
    senha varchar(100) not null,
    tipoUsuario char(5) not null,
    fkEmpresa int not null,
    foreign key (fkEmpresa) references empresa (idEmpresa)
);

create table computador(
	idComputador int primary key auto_increment,
    nome varchar(100) not null,
    so varchar(100) not null,
    processador varchar(100) not null,
    qtdMemoria varchar(8) not null,
    placaMae varchar(100) not null, 
    placaVideo varchar(100) not null,
    disco varchar(100) not null,
    fkArena int not null,
    foreign key (fkArena) references arena (idArena)
);


-- Função para buscar o ID da empresa de acordo com o CNPJ
DELIMITER $$
CREATE FUNCTION fn_empresa(fnCnpj char(18)) 
RETURNS int
deterministic
BEGIN
	DECLARE vId int;
    
    set vId = (select idEmpresa from empresa where CNPJ = fnCnpj);
    
    return(vId);
END$$;
DELIMITER ;