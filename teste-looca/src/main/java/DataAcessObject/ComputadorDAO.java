package dataAcessObject;
import conexao.Conexao;
import entidades.Computador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComputadorDAO {
    // A classe vai servir para criar metodos que fazem conexao com o banco
        Integer idComputador = 0;
        Computador computador = new Computador();
        public static boolean cadastrarComputador (Computador computador){
            String sql = "INSERT INTO tbcomputador (SistemaOperacional, processador, discoTotal, memoriaTotal) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = null;
            try{
                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setString(1, computador.getSO());
                ps.setString(2, computador.getProcessador());
                ps.setLong(3, computador.getDiscoTotal());
                ps.setLong(4, computador.getMemoriaTot());
                ps.execute();
            } catch (SQLException e ){
                e.printStackTrace();
            }
            return false;
        }

        public static String pegarIdComputador (Computador computador){
            String sql = "SELECT idComputador FROM tbcomputador";
            PreparedStatement ps = null;
            ResultSet rs = null; // ResultSet é uma classe utilizada para poder realizar os selects
            try{
                ps = Conexao.getConexao().prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()) { // o  next é para ele mover para a prox. linha
                   computador.setId(rs.getInt(1));
                   System.out.println(computador.getId());
                }
                ps.execute();
            } catch (SQLException e ){
                e.printStackTrace();
            }
            return sql;
        }
}

