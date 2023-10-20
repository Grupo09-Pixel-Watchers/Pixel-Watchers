var database = require("../database/config")


function cadastrarArena(nomeArena, cepArena, logradouroArena, numeroArena, bairroArena, cidadeArena, ufArena, empresaArena) {
    var instrucao = `INSERT INTO arena VALUES (NULL, '${nomeArena}', '${cepArena}', '${logradouroArena}', '${numeroArena}', '${bairroArena}',  '${cidadeArena}', '${ufArena}', '${empresaArena}')`;
    return database.executar(instrucao);
}

module.exports = {
    cadastrarArena
};