var database = require("../database/config")


function buscarAlertasPC(idComputador) {

    instrucaoSql = `
        SELECT 
            (SELECT COUNT(*) 
            FROM Alerta 
            WHERE fkComputador = '${idComputador}' 
            AND dtHoraAlerta > DATE_SUB(NOW(), INTERVAL 24 HOUR)) AS quantidadeAlertasUltimas24Horas,
            idAlerta,
            descricao,
            DATE_FORMAT(dtHoraAlerta, '%d:%m:%Y - %H:%i:%s') AS dtHoraAlertaFormatada
            FROM Alerta
            WHERE fkComputador = '${idComputador}' 
            ORDER BY dtHoraAlerta DESC
            LIMIT 1;
    `;
  
    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function buscarAlertasArena(idArena) {

    instrucaoSql1 = `
        SELECT * from Alerta join tbComputador on idComputador = fkComputador where fkArena = ${idArena}
    `;
  
    console.log("Executando a instrução SQL: \n" + instrucaoSql + );
    return database.executar(instrucaoSql) + database.executar();
}

module.exports = {
    buscarAlertasPC,
    buscarAlertasArena,
};