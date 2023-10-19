var database = require("../database/config");

function buscarPorCnpj(cnpj) {
  var query = `select * from empresa where cnpj = '${cnpj}'`;

  return database.executar(query);
}


function cadastrar(nomeEmpresa, razaoSocial, cnpj, telefone) {
  var query = `INSERT INTO empresa VALUES (NULL, '${nomeEmpresa}', '${razaoSocial}', '${cnpj}', '${telefone}');
  `;
  return database.executar(query);
}

module.exports = {buscarPorCnpj, cadastrar};
