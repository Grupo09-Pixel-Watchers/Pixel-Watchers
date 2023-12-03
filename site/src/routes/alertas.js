var express = require("express");
var router = express.Router();

var alertaController = require("../controllers/alertaController");

router.get("/buscarAlertasPC/:idComputador", function (req, res) {
    alertaController.buscarAlertasPC(req, res);
});

router.get("/buscarAlertasArena/:idArena", function (req, res) {
    alertaController.buscarAlertasArena(req, res);
});

module.exports = router;