package dataAcessObject;
import conexao.Conexao;
import entidades.Computador;
import entidades.MemoriaEmUso;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemoriaDAO {
    public static void cadastrarMemoriaEmUso(MemoriaEmUso memoria, Computador computador) {
            String sql = "INSERT INTO status_pc (dtCaptura, memoriaUso, processadorUso, fkComputador) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, memoria.getIdMemoria());
            ps.setDouble(2, memoria.getMemoriaUso());
            ps.setDouble(3, memoria.getProcessadorUso());
            ps.setInt(4, computador.getId());
            ps.execute();

            System.out.printf("""
                    ||==========================||
                    || Registros da máquina     ||
                    ||==========================||
                    || Memória: %d      ||
                    || CPU: %f           ||
                    ||==========================||
                    """, memoria.getMemoriaUso(), memoria.getProcessadorUso());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    public static void pegarDtCaptura(LocalDateTime data) {
//        MemoriaEmUso mem = new MemoriaEmUso();
//        mem.setIdMemoria(String.valueOf(data));
//        System.out.println(mem.getIdMemoria());
//    }
//
//    public static void pegarMemoriaEmUso(Long memoria) {
//        MemoriaEmUso memoriaEmUso = new MemoriaEmUso();
//        memoriaEmUso.setMemoriaUso(String.valueOf(memoria));
//        System.out.println(memoriaEmUso.getIdMemoria());
//    }

}

