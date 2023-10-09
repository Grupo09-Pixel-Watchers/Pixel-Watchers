CREATE DATABASE PixelWatchers;
USE PixelWatchers;

CREATE TABLE tbEmpresa
(idEmpresa INT PRIMARY KEY AUTO_INCREMENT,
nomeEmpresa VARCHAR(45),
razaoSocial VARCHAR(45),
cnpj VARCHAR(45),
telefone VARCHAR(45));

CREATE TABLE tbUsuario
(idUsuario INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(45),
sobrenome VARCHAR(45),
senha VARCHAR(45),
tipoUsuario VARCHAR(45),
fkEmpresa INT,
	CONSTRAINT fkEmpresa FOREIGN KEY (fkEmpresa)
		REFERENCES tbEmpresa (idEmpresa));