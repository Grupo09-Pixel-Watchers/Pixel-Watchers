var database = require("../database/config")


function buscarAlertasPC(idComputador) {

    var instrucaoSql = ''

    if (process.env.AMBIENTE_PROCESSO == 'desenvolvimento') {
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
    }
    else {
        instrucaoSql =
            `
        DECLARE @quantidadeAlertasUltimas24Horas INT;

        SELECT
            @quantidadeAlertasUltimas24Horas = COUNT(*)
        FROM Alerta
        WHERE fkComputador = '${idComputador}'
            AND dtHoraAlerta > DATEADD(HOUR, -24, GETDATE());

        SELECT TOP 1
            @quantidadeAlertasUltimas24Horas AS quantidadeAlertasUltimas24Horas,
            idAlerta,
            descricao,
            FORMAT(dtHoraAlerta, 'dd:MM:yyyy - HH:mm:ss') AS dtHoraAlertaFormatada
        FROM Alerta
        WHERE fkComputador = '${idComputador}'
        ORDER BY dtHoraAlerta DESC;
        `
    }



    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function buscarAlertaMaisRecente(idArena) {

    var instrucaoSql = ''

    if (process.env.AMBIENTE_PROCESSO == 'desenvolvimento') {
        instrucaoSql = `
        SELECT a.idAlerta, a.descricao, a.fkComputador, c.apelidoPc, a.dtHoraAlerta, a.tipoAlerta
        FROM Alerta a
        JOIN tbComputador c ON a.fkComputador = c.idComputador
        WHERE fkArena = ${idArena}
        ORDER BY a.dtHoraAlerta DESC
        LIMIT 1;
    `;
    }
    else {
        instrucaoSql =
            `
        SELECT TOP 1
            a.idAlerta,
            a.descricao,
            a.fkComputador,
            c.apelidoPc,
            a.dtHoraAlerta,
            a.tipoAlerta
        FROM
            Alerta a
            INNER JOIN tbComputador c ON a.fkComputador = c.idComputador
        WHERE
            fkArena = ${idArena}
        ORDER BY
            a.dtHoraAlerta DESC;
        `
    }

    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function buscarPcMaisAlertas(idArena) {

    var instrucaoSql = ''

    if (process.env.AMBIENTE_PROCESSO == 'desenvolvimento') {

        instrucaoSql = `
    SELECT c.idComputador, c.apelidoPc, COUNT(*) as total_alertas
    FROM Alerta a
    JOIN tbComputador c ON a.fkComputador = c.idComputador
    WHERE c.fkArena = ${idArena}
    AND a.dtHoraAlerta >= NOW() - INTERVAL 24 HOUR
    GROUP BY c.idComputador
    ORDER BY total_alertas DESC
    LIMIT 1;`;
    }
    else {
        instrucaoSql = `
        SELECT TOP 1
            c.idComputador,
            c.apelidoPc,
            COUNT(*) as total_alertas
        FROM
            Alerta a
            INNER JOIN tbComputador c ON a.fkComputador = c.idComputador
        WHERE
            c.fkArena = ${idArena}
            AND a.dtHoraAlerta >= DATEADD(HOUR, -24, GETDATE())
        GROUP BY
            c.idComputador, c.apelidoPc
        ORDER BY
            total_alertas DESC;`
    }

    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function buscarTipoAlerta(idComputador) {

    var instrucaoSql = ''

    if (process.env.AMBIENTE_PROCESSO == 'desenvolvimento') {
        instrucaoSql = `
    SELECT tipoAlerta, COUNT(*) AS qtdRepeticoes
    FROM Alerta
    WHERE fkComputador = '${idComputador}'
    GROUP BY tipoAlerta
    ORDER BY qtdRepeticoes DESC
    LIMIT 1;`;
    }
    else {

        instrucaoSql =
            `
            SELECT TOP 1
                tipoAlerta,
                COUNT(*) AS qtdRepeticoes
            FROM
                Alerta
            WHERE
                fkComputador = '${idComputador}'
            GROUP BY
                tipoAlerta
            ORDER BY
                qtdRepeticoes DESC;`
    }
    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}






module.exports = {
    buscarAlertasPC,
    buscarAlertaMaisRecente,
    buscarPcMaisAlertas,
    buscarTipoAlerta
};