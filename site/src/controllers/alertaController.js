var alertaModel = require("../models/alertaModel");


function buscarAlertasPC(req, res) {

    var idComputador = req.params.idComputador;

    alertaModel.buscarAlertasPC(idComputador).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
            return;
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar os alertas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}

function buscarAlertaMaisRecente(req, res) {

    var idArena = req.params.idArena;

    alertaModel.buscarAlertaMaisRecente(idArena).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
            return;
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar os alertas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}

function buscarPcMaisAlertas(req, res) {

    var idArena = req.params.idArena;

    alertaModel.buscarPcMaisAlertas(idArena).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
            return;
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar os alertas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}

function buscarTipoAlerta(req, res) {

    var idComputador = req.params.idComputador;

    alertaModel.buscarTipoAlerta(idComputador).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
            return;
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar os alertas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}

module.exports = {
    buscarAlertasPC,
    buscarAlertaMaisRecente,
    buscarPcMaisAlertas,
    buscarTipoAlerta
}