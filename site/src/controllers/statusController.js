var statusModel = require("../models/statusModel");

function buscarVisaoGeral(req, res) {

    const limite_linhas = 1;

    var idComputador = req.params.idComputador;
    var idArena = req.params.idArena

    console.log(`Recuperando as ultimas ${limite_linhas} medidas`);

    statusModel.buscarVisaoGeral(idComputador, limite_linhas, idArena).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar as ultimas medidas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}


function buscarVisaoEspecifica(req, res) {

    const limite_linhas = 5;

    var idComputador = req.params.idComputador;

    console.log(`Recuperando as ultimas ${limite_linhas} medidas`);

    statusModel.buscarVisaoEspecifica(idComputador, limite_linhas).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar as ultimas medidas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}
module.exports = {
    buscarVisaoGeral,
    buscarVisaoEspecifica
}