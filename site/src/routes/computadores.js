var express = require("express");
var router = express.Router();

var computadorController = require("../controllers/computadorController");

router.get("/buscarPcsDaArena/:idArena", function (req, res) {
    computadorController.buscarPcsDaArena(req, res);
})


module.exports = router;