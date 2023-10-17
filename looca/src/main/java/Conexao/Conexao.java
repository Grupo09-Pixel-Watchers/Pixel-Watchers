package Conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    //Variável do link do banco de dados
    private static final String url = "jdbc:mysql://localhost:3306/prj_sprint";

    //Variável do usuario do banco
    private static final String user = "root";

    //Variável da senha do banco
    private static final String password = "";


    private static Connection conn; // objeto p/ conexão utilizando a classe Connection

    // Método para verificar se a conexao foi bem sucedida
    public static Connection getConexao(){
        try {
            if (conn == null){
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            }else {
                return conn;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    // Se a conexão for nula, ele cria a conexão passando a URL, usuario e senha, caso contrário ele
    // retorna a conexão
}
