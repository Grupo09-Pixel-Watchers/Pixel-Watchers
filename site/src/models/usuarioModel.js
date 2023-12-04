var database = require("../database/config")

function autenticar(email, senha) {
    var instrucao = `SELECT idUsuario, nome, sobrenome, email, tipoUsuario, fkEmpresa, nomeFantasia FROM tbUsuario join tbEmpresa on idEmpresa = fkEmpresa WHERE email = '${email}' AND senha = '${senha}';`;
    console.log("Executando a instrução SQL: \n" + instrucao);
    return database.executar(instrucao);
}

// Coloque os mesmos parâmetros aqui. Vá para a var instrucao
function cadastrarAdmin(nomeUsuario, sobrenomeUsuario, emailUsuario, senhaUsuario, cnpj) {
    var instrucao = `INSERT INTO tbUsuario (nome, sobrenome, email, senha, tipoUsuario, fkEmpresa) VALUES ('${nomeUsuario}', '${sobrenomeUsuario}', '${emailUsuario}', '${senhaUsuario}', 'admin', (select idEmpresa from tbEmpresa where cnpj = '${cnpj}'))`;
    return database.executar(instrucao);
}

function cadastrarNovoUser(nomeUsuario, sobrenomeUsuario, emailUsuario, senhaUsuario, tpUsuario, empresaUsuario) {
    var instrucao = `INSERT INTO tbUsuario (nome, sobrenome, email, senha, tipoUsuario, fkEmpresa) VALUES ('${nomeUsuario}', '${sobrenomeUsuario}', '${emailUsuario}', '${senhaUsuario}', '${tpUsuario}', '${empresaUsuario}')`;
    return database.executar(instrucao);
}

function buscarPorEmail(emailUsuario) {
    var query = `select * from tbUsuario where email = '${emailUsuario}'`;
  
    return database.executar(query);
}

function buscarUsuariosDaEmpresa(idEmpresa) {

    instrucaoSql = `select * from tbUsuario a where fkEmpresa = ${idEmpresa}`;
  
    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function buscarDadosAtuais(idUsuario) {

    instrucaoSql = `select * from tbUsuario a where idUsuario = ${idUsuario}`;
  
    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

function editarUsuario(novoNome, novoSobrenome, novoEmail, novoTpUser, idUsuario){
    var instrucaoSql = `UPDATE tbUsuario SET nome = '${novoNome}', sobrenome = '${novoSobrenome}', email = '${novoEmail}', tipoUsuario = '${novoTpUser}' WHERE idUsuario = ${idUsuario}`

    console.log("Executando a instrução SQL: \n" + instrucaoSql);
    return database.executar(instrucaoSql);
}

module.exports = {
    autenticar,
    cadastrarAdmin,
    cadastrarNovoUser,
    buscarPorEmail,
    buscarUsuariosDaEmpresa,
    buscarDadosAtuais,
    editarUsuario,
};