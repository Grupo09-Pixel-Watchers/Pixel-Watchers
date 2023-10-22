var database = require("../database/config");

function buscarPorCnpj(cnpj) {
  var query = `select * from tbEmpresa where cnpj = '${cnpj}'`;

  return database.executar(query);
}


function cadastrar(nomeEmpresa, razaoSocial, cnpj, telefone) {
  var query = `INSERT INTO tbEmpresa VALUES (NULL, '${nomeEmpresa}', '${razaoSocial}', '${cnpj}', '${telefone}', 60);
  `;
  return database.executar(query);
}

module.exports = {buscarPorCnpj, cadastrar};
