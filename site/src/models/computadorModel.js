var database = require("../database/config")



function buscarPcsDaArena(idArena) {

    instrucaoSql = `select * from tbComputador where fkArena = ${idArena}`;
  
    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}
module.exports = {
    buscarPcsDaArena
};