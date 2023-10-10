CREATE DATABASE prj_sprint;
USE prj_sprint;

CREATE TABLE tbEmpresa(
	idEmpresa INT PRIMARY KEY AUTO_INCREMENT,
    nomeFantasia VARCHAR(150) NOT NULL,
    razaoSocial VARCHAR(150) NOT NULL,
    cnpj CHAR(14) NOT NULL,
    telefone char(10) NOT NULL
);

CREATE TABLE tbUsuario(
	idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    senha VARCHAR(200) NOT NULL,
    tipoUsuario VARCHAR(20),
	fkEmpresa INT,
    FOREIGN KEY (fkEmpresa) REFERENCES tbEmpresa (idEmpresa)
);

CREATE TABLE tbArena(
	idArena INT PRIMARY KEY AUTO_INCREMENT,
    nomeArena VARCHAR(150),
    cep CHAR(11) NOT NULL,
    logradouro VARCHAR(100),
    numero INT(11),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    fkEmpresa INT,
    FOREIGN KEY (fkEmpresa) REFERENCES tbEmpresa (idEmpresa)
);

CREATE TABLE tbComputador(
	idComputador INT PRIMARY KEY AUTO_INCREMENT,
    sistemaOperacional VARCHAR(30),
    processador VARCHAR(100),
    placaMae VARCHAR(100),
    discoTotal LONG,
    memoriaTotal LONG,
    fkArena INT,
    FOREIGN KEY (fkArena) REFERENCES tbArena (idArena)
);	

CREATE TABLE status_pc(
	dtCaptura DATETIME PRIMARY KEY,
    memoriaUso LONG, 
    processadorUso DOUBLE,
    discoDisponivel LONG,
    dispositivosConectados INT(11),
	fkComputador INT,
    FOREIGN KEY (fkComputador) REFERENCES tbComputador (idComputador)
);