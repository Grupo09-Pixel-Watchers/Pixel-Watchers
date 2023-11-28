var express = require("express");
var router = express.Router();

var usuarioController = require("../controllers/usuarioController");

//Recebendo os dados do html e direcionando para a função cadastrar de usuarioController.js
router.post("/cadastrarAdmin", function (req, res) {
    usuarioController.cadastrarAdmin(req, res);
})

router.post("/cadastrarNovoUser", function (req, res) {
    usuarioController.cadastrarNovoUser(req, res);
})

router.post("/autenticar", function (req, res) {
    usuarioController.autenticar(req, res);
});

router.get("/buscarUsuariosDaEmpresa/:idEmpresa", function (req, res) {
    usuarioController.buscarUsuariosDaEmpresa(req, res);
})

router.get("/buscarDadosAtuais/:idUsuario", function (req, res) {
    usuarioController.buscarDadosAtuais(req, res);
})

router.put("/editarUsuario/:idUsuario", function (req, res) {
    usuarioController.editarUsuario(req, res);
})

module.exports = router;