var express = require("express");
var router = express.Router();

var statusController = require("../controllers/statusController");

router.get("/geral/:idComputador,:idArena", function (req, res) {
    statusController.buscarVisaoGeral(req, res);
});

router.get("/especifico/:idComputador", function (req, res) {
    statusController.buscarVisaoEspecifica(req, res);
});

router.get("/tempo-real/:idComputador", function (req, res) {
    statusController.buscarTempoReal(req, res);
});


module.exports = router;