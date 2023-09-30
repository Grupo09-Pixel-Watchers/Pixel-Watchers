package DataAcessObject;
import conexao.Conexao;
import entidades.Computador;
import entidades.MemoriaEmUso;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MemoriaDAO {

    public static void pegarDtCaptura(LocalDateTime data) {
        MemoriaEmUso mem = new MemoriaEmUso();
        mem.setIdMemoria(String.valueOf(data));
        System.out.println(mem.getIdMemoria());
    }

    public static void pegarMemoriaEmUso(Long memoria) {
        MemoriaEmUso mem = new MemoriaEmUso();
        mem.setMemoriaUso(memoria);
        System.out.println(mem.getIdMemoria());
    }


    public static void cadastrarMemoriaEmUso(MemoriaEmUso memoria, Computador computador) {
        String sql = "INSERT INTO stts_pc (dtCaptura, memoriaUso, fk_pc) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, memoria.getIdMemoria());
            ps.setLong(2, memoria.getMemoriaUso());
            ps.setInt(3, computador.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

