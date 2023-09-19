package DataAcessObject;

import conexao.Conexao;
import entidades.Computador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComputadorDAO {
    // A classe vai servir para criar metodos que fazem conexao com o banco
        Integer idSistema = 0;
        Computador computador = new Computador();
        public static boolean cadastrarComputador (Computador computador){
            String sql = "INSERT INTO sistema (SO, fabricante, arquitetura, dtIniciado) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = null;
            try{
                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setString(1, computador.getSO());
                ps.setString(2, computador.getFabricante());
                ps.setString(3, String.valueOf(computador.getArquitetura()));
                ps.setString(4, String.valueOf(computador.getDtInicializado()));

                ps.execute();
            } catch (SQLException e ){
                e.printStackTrace();
            }
            return false;
        }

    public static String pegarIdSistema (Computador sistema){
        String sql = "SELECT idSistema FROM SISTEMA";
        PreparedStatement ps = null;
        ResultSet rs = null; // ResultSet é uma classe utilizada para poder realizar os selects
        try{
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) { // o  next é para ele mover para a prox. linha
               sistema.setId(rs.getInt(1));
                System.out.println(sistema.getId());
            }
            ps.execute();
        } catch (SQLException e ){
            e.printStackTrace();
        }
        return sql;
    }
    public static boolean cadastrarMemoria(Computador computador){
        String sql = "INSERT INTO stts_pc (memoria, fk_pc) VALUES (?, ?)";
        PreparedStatement ps = null;
        try{
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setLong(1, computador.getMemoria());
            ps.setInt(2, computador.getId());
            ps.execute();
        } catch (SQLException e ){
            e.printStackTrace();
        }
        return false;
    }


}

