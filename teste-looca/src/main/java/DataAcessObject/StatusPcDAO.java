package dataAcessObject;

import conexao.Conexao;
import entidades.Computador;
import entidades.MemoriaEmUso;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatusPcDAO {
    public static void cadastrarCapturas(MemoriaEmUso memoria, Computador computador) {
        String sql = "INSERT INTO status_pc (dtCaptura, memoriaUso, processadorUso, fkComputador) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, memoria.getIdMemoria());
            ps.setLong(2, memoria.getMemoriaUso());
            ps.setDouble(3, memoria.getProcessadorUso());
            ps.setInt(4, computador.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
