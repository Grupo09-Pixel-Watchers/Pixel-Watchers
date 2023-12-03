var database = require("../database/config")


function cadastrarArena(nomeArena, cepArena, logradouroArena, numeroArena, bairroArena, cidadeArena, ufArena, empresaArena) {
    var instrucao = `INSERT INTO tbArena
    (nomeArena, cep, logradouro, numero, bairro, cidade, uf, fkEmpresa) 
    VALUES ('${nomeArena}', '${cepArena}', '${logradouroArena}', '${numeroArena}', '${bairroArena}',  '${cidadeArena}', '${ufArena}', '${empresaArena}')`;
    return database.executar(instrucao);
}


function buscarArenasDaEmpresa(idEmpresa) {

    instrucaoSql = `select * from tbArena a where fkEmpresa = ${idEmpresa}`;
  
    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function buscarDadosAtuais(idArena) {

    instrucaoSql = `select * from tbArena a where idArena = ${idArena}`;
  
    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function editarArena(novoNome, novoCep, novoLog, novoNumero, novoBairro, novaCidade, novaUf, idArena){
    var instrucaoSql = `UPDATE tbArena SET nomeArena = '${novoNome}', cep = '${novoCep}', logradouro = '${novoLog}', numero = '${novoNumero}', bairro = '${novoBairro}', cidade = '${novaCidade}', uf = '${novaUf}' WHERE idArena = ${idArena}`

    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}
module.exports = {
    cadastrarArena,
    buscarArenasDaEmpresa,
    buscarDadosAtuais,
    editarArena
};