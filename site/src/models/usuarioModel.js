var database = require("../database/config")

function autenticar(email, senha) {
    var instrucao = `SELECT idUsuario, nome, sobrenome, email, tipoUsuario, fkEmpresa, nomeFantasia FROM tbUsuario join tbEmpresa on idEmpresa = fkEmpresa WHERE email = '${email}' AND senha = '${senha}';`;
    console.log("Executando a instrução SQL: \n" + instrucao);
    return database.executar(instrucao);
}

// Coloque os mesmos parâmetros aqui. Vá para a var instrucao
function cadastrarAdmin(nomeUsuario, sobrenomeUsuario, emailUsuario, senhaUsuario, cnpj) {
    var instrucao = `INSERT INTO tbUsuario VALUES (NULL, '${nomeUsuario}', '${sobrenomeUsuario}', '${emailUsuario}', '${senhaUsuario}', 'admin', fn_empresa('${cnpj}'))`;
    return database.executar(instrucao);
}

function cadastrarNovoUser(nomeUsuario, sobrenomeUsuario, emailUsuario, senhaUsuario, tpUsuario, empresaUsuario) {
    var instrucao = `INSERT INTO tbUsuario VALUES (NULL, '${nomeUsuario}', '${sobrenomeUsuario}', '${emailUsuario}', '${senhaUsuario}', '${tpUsuario}', '${empresaUsuario}')`;
    return database.executar(instrucao);
}

function buscarPorEmail(emailUsuario) {
    var query = `select * from tbUsuario where email = '${emailUsuario}'`;
  
    return database.executar(query);
  }

module.exports = {
    autenticar,
    cadastrarAdmin,
    cadastrarNovoUser,
    buscarPorEmail
};