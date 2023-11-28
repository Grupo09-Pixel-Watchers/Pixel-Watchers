package Conexao;
import Logger.Mensageiro;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    //Atributos para conexão do banco de dados
    private static final String url = "jdbc:mysql://localhost:3306/prj_sprint";
    private static final String user = "UserDB";
    private static final String password = "1234";
    private static Connection conn; // objeto p/ conexão utilizando a classe Connection


    // Método para verificar se a conexao foi bem sucedida
    public static Connection getConexao() throws IOException {
        try {
            if (conn == null){
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            }else {
                return conn;
            }
        } catch (SQLException e){
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            Mensageiro.generateLog("ERRO - " + stringWriter);
            return null;
        }
    }
    // Se a conexão for nula, ele cria a conexão passando a URL, usuario e senha, caso contrário ele
    // retorna a conexão
}
