var arenaModel = require("../models/arenaModel");

function cadastrarArena(req, res) {
    var nomeArena = req.body.nomeArenaServer;
    var cepArena = req.body.cepServer;
    var logradouroArena = req.body.logradouroServer;
    var numeroArena = req.body.numeroServer;
    var bairroArena = req.body.bairroServer;
    var cidadeArena = req.body.cidadeServer;
    var ufArena = req.body.ufServer;
    var empresaArena = req.body.empresaServer;

    arenaModel.cadastrarArena(nomeArena, cepArena, logradouroArena, numeroArena, bairroArena, cidadeArena, ufArena, empresaArena)
        .then(
            function (resultado) {
                res.json(resultado);
            }
        ).catch(
            function (erro) {
                console.log(erro);
                console.log(
                    "\nHouve um erro ao realizar o cadastro! Erro: ",
                    erro.sqlMessage
                );
                res.status(500).json(erro.sqlMessage);
            }
        );
}


function buscarArenasDaEmpresa(req, res) {

    var idEmpresa = req.params.idEmpresa;

    arenaModel.buscarArenasDaEmpresa(idEmpresa).then(function (resultado) {
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
    cadastrarArena,
    buscarArenasDaEmpresa
}