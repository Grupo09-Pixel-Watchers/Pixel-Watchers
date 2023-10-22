var express = require("express");
var router = express.Router();

var arenaController = require("../controllers/arenaController");

router.post("/cadastrarArena", function (req, res) {
    arenaController.cadastrarArena(req, res);
})

router.get("/buscarArenasDaEmpresa/:idEmpresa", function (req, res) {
    arenaController.buscarArenasDaEmpresa(req, res);
})


module.exports = router;