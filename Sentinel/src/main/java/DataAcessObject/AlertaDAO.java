package DataAcessObject;

import Conexao.Conexao;
import Entidades.Alerta;
import Entidades.Computador;
import Slack.Slack;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlertaDAO {

    static JSONObject json = new JSONObject();
    static String textoAlerta;

    public static boolean cadastrarAlerta(Alerta alerta, Computador computador, String tipoAlerta) throws IOException, InterruptedException {
        String sql = "INSERT INTO Alerta (descricao, dtHoraAlerta, caminhoArquivo, tipoAlerta, fkComputador) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, alerta.getDescricao());
            ps.setString(2, alerta.getDtHoraAlerta());
            ps.setString(3, alerta.getCaminhoArquivo());
            ps.setString(4, tipoAlerta);  // Tipo de alerta (Pasta ou Arquivo)
            ps.setString(5, computador.getId());
            int rowsAffected = ps.executeUpdate();

            textoAlerta = String.format("""
                    ALERTA NO MONITORAMENTO!
                    Descrição: %s
                    Data e Hora: %s
                    Caminho do arquivo: %s
                    Tipo de alerta: %s
                    Computador: %s
                    """, alerta.getDescricao(), alerta.getDtHoraAlerta(), alerta.getCaminhoArquivo(), tipoAlerta, computador.getId());

            json.put("text", textoAlerta);
            Slack.sendMessage(json);

            return rowsAffected > 0; // Retorna verdadeiro se pelo menos uma linha for afetada

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Em caso de falha
    }
}
