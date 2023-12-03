var express = require("express");
var router = express.Router();

var alertaController = require("../controllers/alertaController");

router.get("/buscarAlertasPC/:idComputador", function (req, res) {
    alertaController.buscarAlertasPC(req, res);
});

router.get("/buscarAlertaMaisRecente/:idArena", function (req, res) {
    alertaController.buscarAlertaMaisRecente(req, res);
});

router.get("/buscarPcMaisAlertas/:idArena", function (req, res) {
    alertaController.buscarPcMaisAlertas(req, res);
});

router.get("/buscarTipoAlerta/:idComputador", function (req, res) {
    alertaController.buscarTipoAlerta(req, res);
});

module.exports = router;