var express = require("express");
var router = express.Router();

var arenaController = require("../controllers/arenaController");

router.post("/cadastrarArena", function (req, res) {
    arenaController.cadastrarArena(req, res);
})


module.exports = router;