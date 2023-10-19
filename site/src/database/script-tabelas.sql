create database pixelWatchers;

use pixelWatchers;

create table empresa(
	idEmpresa int primary key auto_increment,
    nomeFantasia varchar(100),
    razaoSocial varchar(100),
    CNPJ char (18),
    telefone char(15)
);

create table arena(
	idArena int primary key auto_increment,
    nomeArena varchar(100),
    cep char(9),
    logradouro varchar(100),
    numero varchar(6),
    bairro varchar(100),
    cidade varchar(100),
    uf char(2),
    fkEmpresa int,
    foreign key (fkEmpresa) references empresa (idEmpresa)
);

create table usuario(
	idUsuario int primary key auto_increment,
    nome varchar(100),
    sobrenome varchar(110),
    email varchar(100),
    senha varchar(100),
    tipoUsuario char(5),
    fkEmpresa int,
    foreign key (fkEmpresa) references empresa (idEmpresa)
);

create table computador(
	idComputador int primary key auto_increment,
    nome varchar(100),
    so varchar(100),
    processador varchar(100),
    qtdMemoria varchar(8),
    placaMae varchar(100),
    placaVideo varchar(100),
    disco varchar(100),
    fkArena int,
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