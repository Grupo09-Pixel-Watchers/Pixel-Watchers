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

function buscarAlertaMaisRecente(idArena) {

    instrucaoSql = `
    SELECT a.idAlerta, a.descricao, a.fkComputador, c.apelidoPc, a.dtHoraAlerta, a.tipoAlerta
    FROM Alerta a
    JOIN tbComputador c ON a.fkComputador = c.idComputador
    WHERE fkArena = ${idArena}
    ORDER BY a.dtHoraAlerta DESC
    LIMIT 1;
    `;

    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function buscarPcMaisAlertas(idArena) {

    instrucaoSql = `
    SELECT c.idComputador, c.apelidoPc, COUNT(*) as total_alertas
    FROM Alerta a
    JOIN tbComputador c ON a.fkComputador = c.idComputador
    WHERE c.fkArena = ${idArena}
    AND a.dtHoraAlerta >= NOW() - INTERVAL 24 HOUR
    GROUP BY c.idComputador
    ORDER BY total_alertas DESC
    LIMIT 1;`;

    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function buscarTipoAlerta(idComputador) {

    instrucaoSql = `
    SELECT tipoAlerta, COUNT(*) AS qtdRepeticoes
    FROM Alerta
    WHERE fkComputador = '${idComputador}'
    GROUP BY tipoAlerta
    ORDER BY qtdRepeticoes DESC
    LIMIT 1;`;

    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}






module.exports = {
    buscarAlertasPC,
    buscarAlertaMaisRecente,
    buscarPcMaisAlertas,
    buscarTipoAlerta
};