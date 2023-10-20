var usuarioModel = require("../models/usuarioModel");

// function autenticar(req, res) {
//     var email = req.body.emailServer;
//     var senha = req.body.senhaServer;

//     if (email == undefined) {
//         res.status(400).send("Seu email está undefined!");
//     } else if (senha == undefined) {
//         res.status(400).send("Sua senha está indefinida!");
//     } else {

//         usuarioModel.autenticar(email, senha)
//             .then(
//                 function (resultadoAutenticar) {
//                     console.log(`\nResultados encontrados: ${resultadoAutenticar.length}`);
//                     console.log(`Resultados: ${JSON.stringify(resultadoAutenticar)}`); // transforma JSON em String

//                     if (resultadoAutenticar.length == 1) {
//                         console.log(resultadoAutenticar);

//                         // aquarioModel.buscarAquariosPorEmpresa(resultadoAutenticar[0].empresaId)
//                         //     .then((resultadoAquarios) => {
//                         //         if (resultadoAquarios.length > 0) {
//                         //             res.json({
//                         //                 id: resultadoAutenticar[0].id,
//                         //                 email: resultadoAutenticar[0].email,
//                         //                 nome: resultadoAutenticar[0].nome,
//                         //                 senha: resultadoAutenticar[0].senha,
//                         //                 aquarios: resultadoAquarios
//                         //             });
//                         //         } else {
//                         //             res.status(204).json({ aquarios: [] });
//                         //         }
//                         //     })
//                     } else if (resultadoAutenticar.length == 0) {
//                         res.status(403).send("Email e/ou senha inválido(s)");
//                     } else {
//                         res.status(403).send("Mais de um usuário com o mesmo login e senha!");
//                     }
//                 }
//             ).catch(
//                 function (erro) {
//                     console.log(erro);
//                     console.log("\nHouve um erro ao realizar o login! Erro: ", erro.sqlMessage);
//                     res.status(500).json(erro.sqlMessage);
//                 }
//             );
//     }

// }


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

module.exports = {
    cadastrarAdmin,
    autenticar
}