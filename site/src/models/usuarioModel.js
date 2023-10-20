var database = require("../database/config")

function autenticar(email, senha) {
    var instrucao = `SELECT idUsuario, nome, sobrenome, email, tipoUsuario, fkEmpresa, nomeFantasia FROM usuario join empresa on idEmpresa = fkEmpresa WHERE email = '${email}' AND senha = '${senha}';`;
    console.log("Executando a instrução SQL: \n" + instrucao);
    return database.executar(instrucao);
}

// Coloque os mesmos parâmetros aqui. Vá para a var instrucao
function cadastrarAdmin(nomeUsuario, sobrenomeUsuario, emailUsuario, senhaUsuario, cnpj) {
    var instrucao = `INSERT INTO usuario VALUES (NULL, '${nomeUsuario}', '${sobrenomeUsuario}', '${emailUsuario}', '${senhaUsuario}', 'admin', fn_empresa('${cnpj}'))`;
    return database.executar(instrucao);
}

module.exports = {
    autenticar,
    cadastrarAdmin
};