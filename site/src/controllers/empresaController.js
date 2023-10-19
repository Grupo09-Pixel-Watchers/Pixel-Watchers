var empresaModel = require("../models/empresaModel");

function buscarPorCnpj(req, res) {
  var cnpj = req.body.cnpjServer;

  empresaModel.buscarPorCnpj(cnpj).then((resultado) => {
    res.status(200).json(resultado);
  });
}


function cadastrar(req, res) {
  // Crie uma variável que vá recuperar os valores do arquivo cadastro.html
  var nomeEmpresa = req.body.nomeEmpresaServer;
  var razaoSocial = req.body.razaoSocialServer;
  var cnpj = req.body.cnpjServer;
  var telefone = req.body.telefoneServer;

  // Faça as validações dos valores
  if (nomeEmpresa == undefined) {
    res.status(400).send("Seu nome da empresa está undefined!");
  } else if (razaoSocial == undefined) {
    res.status(400).send("Sua razão social está undefined!");
  } else if (cnpj == undefined) {
    res.status(400).send("Seu cnpj está undefined!");
  } else if (telefone == undefined) {
    res.status(400).send("Seu telefone está undefined!");
  } else {

    empresaModel.buscarPorCnpj(cnpj).then((resultado) => {
      if (resultado.length > 0) {
        res
          .status(401)
          .json({ mensagem: `a empresa com o cnpj ${cnpj} já existe` });
      } 
      
      else {
        // Passe os valores como parâmetro e vá para o arquivo usuarioModel.js
        empresaModel.cadastrar(nomeEmpresa, razaoSocial, cnpj, telefone)
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
}

module.exports = {
  buscarPorCnpj,
  cadastrar,
};
