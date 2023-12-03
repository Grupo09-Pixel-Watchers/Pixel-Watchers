var database = require("../database/config");

function buscarVisaoGeral(idArena) {

    instrucaoSql = ''

    if (process.env.AMBIENTE_PROCESSO == "producao") {
        instrucaoSql = `SELECT
            c.idComputador,
            c.apelidoPC,
            ROUND(s.memoriaUso / c.memoriaTotal * 100, 1) as usoMemoria,
            ROUND((c.discoTotal - s.discoDisponivel) / c.discoTotal * 100, 1) as usoDisco,
            ROUND(s.processadorUso, 1) as usoProcessador,
            s.dtHoraCaptura
        FROM
            tbcomputador c
        JOIN
            tbArena a ON c.fkArena = a.idArena
        JOIN
            status_pc s ON c.idComputador = s.fkComputador
        JOIN (
            SELECT
                fkComputador,
                MAX(dtHoraCaptura) AS max_dtHoraCaptura
            FROM
                status_pc
            GROUP BY
                fkComputador
        ) latest ON s.fkComputador = latest.fkComputador AND s.dtHoraCaptura = latest.max_dtHoraCaptura
        WHERE
            a.idArena = ${idArena};`;
    } else if (process.env.AMBIENTE_PROCESSO == "desenvolvimento") {
        instrucaoSql = `
            SELECT
                c.idComputador,
                c.apelidoPC,
                ROUND(s.memoriaUso / c.memoriaTotal * 100, 1) as usoMemoria,
                ROUND((c.discoTotal - s.discoDisponivel) / c.discoTotal * 100, 1) as usoDisco,
                ROUND(s.processadorUso, 1) as usoProcessador,
                s.dtHoraCaptura
            FROM
                tbcomputador c
            JOIN
                tbArena a ON c.fkArena = a.idArena
            JOIN
                status_pc s ON c.idComputador = s.fkComputador
            JOIN (
                SELECT
                    fkComputador,
                    MAX(dtHoraCaptura) AS max_dtHoraCaptura
                FROM
                    status_pc
                GROUP BY
                    fkComputador
            ) latest ON s.fkComputador = latest.fkComputador AND s.dtHoraCaptura = latest.max_dtHoraCaptura
            WHERE
                a.idArena = ${idArena};`;

    } else {
        console.log("\nO AMBIENTE (produção OU desenvolvimento) NÃO FOI DEFINIDO EM app.js\n");
        return
    }

    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function buscarVisaoEspecifica(idComputador, limite_linhas) {

    instrucaoSql = ''

    if (process.env.AMBIENTE_PROCESSO == "producao") {
        instrucaoSql = `
        SELECT
            ROUND(s.memoriaUso, 1) as memoria,
            ROUND(s.processadorUso, 1) as processador,
            ROUND(s.discoDisponivel, 1) as disco,
            ROUND(s.memoriaUso / c.memoriaTotal * 100, 1) as PMemoria,
            ROUND((c.discoTotal - s.discoDisponivel) / c.discoTotal * 100, 1) as PDisco,
            fkComputador as computador,
            FORMAT(dtHoraCaptura, 'HH:mm:ss') as dtHoraCaptura
        FROM
            status_pc as s
        JOIN
            tbComputador as c ON fkComputador = idComputador
        WHERE
            fkComputador = '${idComputador}' -- Substitua @idComputador pelo valor desejado
        ORDER BY
            idCaptura DESC
        OFFSET 0 ROWS -- OFFSET-FETCH é usado para a paginação, você pode ajustar conforme necessário
        FETCH NEXT ${limite_linhas} ROWS ONLY;`;
        
    } else if (process.env.AMBIENTE_PROCESSO == "desenvolvimento") {
        instrucaoSql = `select 
        ROUND(s.memoriaUso, 1) as memoria, 
        ROUND(s.processadorUso, 1) as processador,
        ROUND(s.discoDisponivel, 1) as disco,
        ROUND(s.memoriaUso / c.memoriaTotal * 100, 1) as PMemoria,
        ROUND((c.discoTotal - s.discoDisponivel) / c.discoTotal * 100, 1) as PDisco,
        fkComputador as computador,
                        DATE_FORMAT(dtHoraCaptura,'%H:%i:%s') as dtHoraCaptura
                    from status_pc as s
                    join tbComputador as c on fkComputador = idComputador
                    where fkComputador = '${idComputador}'
                    order by idCaptura desc limit ${limite_linhas}`;
    } else {
        console.log("\nO AMBIENTE (produção OU desenvolvimento) NÃO FOI DEFINIDO EM app.js\n");
        return
    }

    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}


module.exports = {
    buscarVisaoGeral,
    buscarVisaoEspecifica,
}
