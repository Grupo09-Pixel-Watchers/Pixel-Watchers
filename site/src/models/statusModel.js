var database = require("../database/config");

function buscarVisaoGeral(idArena) {

    instrucaoSql = ''

    if (process.env.AMBIENTE_PROCESSO == "producao") {
        instrucaoSql = `select top ${limite_linhas}
        dht11_temperatura as temperatura, 
        dht11_umidade as umidade,  
                        momento,
                        FORMAT(momento, 'HH:mm:ss') as momento_grafico
                    from medida
                    where fk_aquario = ${idComputador}
                    order by id desc`;
    } else if (process.env.AMBIENTE_PROCESSO == "desenvolvimento") {
        instrucaoSql = `SELECT
        c.idComputador,
        c.apelidoPC,
        s.memoriaUso as memoria,
        s.processadorUso as processador,
        s.discoDisponivel as disco,
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
        instrucaoSql = `select top ${limite_linhas}
        dht11_temperatura as temperatura, 
        dht11_umidade as umidade,  
                        momento,
                        FORMAT(momento, 'HH:mm:ss') as momento_grafico
                    from medida
                    where fk_aquario = ${idComputador}
                    order by id desc`;
    } else if (process.env.AMBIENTE_PROCESSO == "desenvolvimento") {
        instrucaoSql = `select 
        memoriaUso as memoria, 
        processadorUso as processador,
        discoDisponivel as disco,
        fkComputador as computador,
                        DATE_FORMAT(dtHoraCaptura,'%H:%i:%s') as dtHoraCaptura
                    from status_pc
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
