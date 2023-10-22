var database = require("../database/config")


function cadastrarArena(nomeArena, cepArena, logradouroArena, numeroArena, bairroArena, cidadeArena, ufArena, empresaArena) {
    var instrucao = `INSERT INTO tbArena VALUES (NULL, '${nomeArena}', '${cepArena}', '${logradouroArena}', '${numeroArena}', '${bairroArena}',  '${cidadeArena}', '${ufArena}', '${empresaArena}')`;
    return database.executar(instrucao);
}


function buscarArenasDaEmpresa(idEmpresa) {

    instrucaoSql = `select * from tbArena a where fkEmpresa = ${idEmpresa}`;
  
    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}
module.exports = {
    cadastrarArena,
    buscarArenasDaEmpresa
};