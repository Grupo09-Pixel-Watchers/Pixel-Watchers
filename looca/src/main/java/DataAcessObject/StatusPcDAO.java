package DataAcessObject;

import Entidades.DiscoDisponivel;
import Entidades.ProcessadorUso;
import Conexao.Conexao;
import Entidades.Computador;
import Entidades.MemoriaEmUso;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatusPcDAO {
    public static void cadastrarCapturas(MemoriaEmUso memoria, ProcessadorUso processador,
                                         DiscoDisponivel discoDisponivel, Computador computador) {
        String sql = "INSERT INTO status_pc (dtCaptura, memoriaUso, processadorUso, discoDisponivel, fkComputador) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, memoria.getIdMemoria());
            ps.setLong(2, memoria.getMemoriaUso());
            ps.setDouble(3, processador.getProcessadorEmUso());
            ps.setDouble(4, discoDisponivel.getDiscoDisponivel());
            ps.setInt(5, computador.getId());
            ps.execute();
            System.out.println("Cadastrou!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
