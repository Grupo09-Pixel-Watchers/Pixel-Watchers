var express = require("express");
var router = express.Router();

var arenaController = require("../controllers/arenaController");

router.post("/cadastrarArena", function (req, res) {
    arenaController.cadastrarArena(req, res);
})

router.get("/buscarArenasDaEmpresa/:idEmpresa", function (req, res) {
    arenaController.buscarArenasDaEmpresa(req, res);
})

router.get("/buscarDadosAtuais/:idArena", function (req, res) {
    arenaController.buscarDadosAtuais(req, res);
})

router.put("/editarArena/:idArena", function (req, res) {
    arenaController.editarArena(req, res);
});

module.exports = router;