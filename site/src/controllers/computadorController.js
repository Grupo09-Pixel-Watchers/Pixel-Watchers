var computadorModel = require("../models/computadorModel");



function buscarPcsDaArena(req, res) {

    var idArena = req.params.idArena;

    computadorModel.buscarPcsDaArena(idArena).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
            return;
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar as arenas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}

module.exports = {
    buscarPcsDaArena
}