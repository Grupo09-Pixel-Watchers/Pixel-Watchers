var usuarioModel = require("../models/usuarioModel");

function autenticar(req, res) {
    var email = req.body.emailServer;
    var senha = req.body.senhaServer;

    usuarioModel.autenticar(email, senha)
        .then(
            function (resultado) {
                console.log(`\nResultados encontrados: ${resultado.length}`);
                console.log(`Resultados: ${JSON.stringify(resultado)}`); // transforma JSON em String

                if (resultado.length == 1) {
                    console.log(resultado);
                    res.json(resultado[0]);
                } else if (resultado.length == 0) {
                    res.status(403).send("Email e/ou senha inválido(s)");
                } else {
                    res.status(403).send("Mais de um usuário com o mesmo login e senha!");
                }
            }
        ).catch(
            function (erro) {
                console.log(erro);
                console.log("\nHouve um erro ao realizar o login! Erro: ", erro.sqlMessage);
                res.status(500).json(erro.sqlMessage);
            }
        );
}


function cadastrarAdmin(req, res) {
    // Crie uma variável que vá recuperar os valores do arquivo cadastro.html
    var nomeUsuario = req.body.nomeUsuarioServer;
    var sobrenomeUsuario = req.body.sobrenomeUsuarioServer;
    var emailUsuario = req.body.emailServer;
    var senhaUsuario = req.body.senhaServer;
    var cnpj = req.body.cnpjServer;

    // Faça as validações dos valores
    if (nomeUsuario == undefined) {
        res.status(400).send("Seu nome está undefined!");
    } else if (sobrenomeUsuario == undefined) {
        res.status(400).send("Seu sobrenome está undefined!");
    } else if (emailUsuario == undefined) {
        res.status(400).send("Seu email está undefined!");
    } else if (senhaUsuario == undefined) {
        res.status(400).send("Sua senha está undefined!");
    } else if (cnpj == undefined) {
        res.status(400).send("Seu cnpj está undefined!");
    } else {

        // Passe os valores como parâmetro e vá para o arquivo usuarioModel.js
        usuarioModel.cadastrarAdmin(nomeUsuario, sobrenomeUsuario, emailUsuario, senhaUsuario, cnpj)
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
}


function cadastrarNovoUser(req, res) {
    var nomeUsuario = req.body.nomeUsuarioServer;
    var sobrenomeUsuario = req.body.sobrenomeUsuarioServer;
    var emailUsuario = req.body.emailUsuarioServer;
    var senhaUsuario = req.body.senhaUsuarioServer;
    var tpUsuario = req.body.tpUsuarioServer;
    var empresaUsuario = req.body.empresaUsuarioServer


    usuarioModel.buscarPorEmail(emailUsuario).then((resultado) => {
        if (resultado.length > 0) {
            var repetido = true
            res
                .status(401)
                .json({ mensagem: `o usuario com o email ${emailUsuario} já existe` });
            return repetido;
        }
        else {

            // Passe os valores como parâmetro e vá para o arquivo usuarioModel.js
            usuarioModel.cadastrarNovoUser(nomeUsuario, sobrenomeUsuario, emailUsuario, senhaUsuario, tpUsuario, empresaUsuario)
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
    });
}

function buscarPorEmail(req, res) {
    var emailUsuario = req.body.emailUsuarioServer;

    usuarioModel.buscarPorEmail(emailUsuario).then((resultado) => {
        res.status(200).json(resultado);
    });
}

function buscarUsuariosDaEmpresa(req, res) {

    var idEmpresa = req.params.idEmpresa;
    console.log(idEmpresa)

    usuarioModel.buscarUsuariosDaEmpresa(idEmpresa).then(function (resultado) {
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

function buscarDadosAtuais(req, res) {

    var idUsuario = req.params.idUsuario;

    usuarioModel.buscarDadosAtuais(idUsuario).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
            return;
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar os dados.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}

function editarUsuario(req, res) {

    var novoNome = req.body.novoNome
    var novoSobrenome = req.body.novoSobrenome
    var novoEmail = req.body.novoEmail
    var novoTpUser = req.body.novoTpUser
    var idUsuario = req.params.idUsuario

    usuarioModel.editarUsuario(novoNome, novoSobrenome, novoEmail, novoTpUser, idUsuario)
        .then(
            function (resultado) {
                res.json(resultado);
            }
        )
        .catch(
            function (erro) {
                console.log(erro);
                console.log("Houve um erro ao realizar o post: ", erro.sqlMessage);
                res.status(500).json(erro.sqlMessage);
            }
        );

}


module.exports = {
    cadastrarAdmin,
    autenticar,
    cadastrarNovoUser,
    buscarPorEmail,
    buscarUsuariosDaEmpresa,
    buscarDadosAtuais,
    editarUsuario,
}