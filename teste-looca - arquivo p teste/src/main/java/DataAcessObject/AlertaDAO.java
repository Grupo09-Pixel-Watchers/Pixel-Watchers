package DataAcessObject;

import Conexao.Conexao;
import Entidades.Alerta;
import Entidades.Computador;
import IntegracaoSlack.Slack;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlertaDAO {

    public static boolean cadastrarAlerta(Alerta alerta, Computador computador, String tipoAlerta) throws InterruptedException {
        String textoAlerta;
        JSONObject json = new JSONObject();

        String sql = "INSERT INTO alerta (descricao, dtHoraAlerta, caminhoArquivo, tipoAlerta, fkComputador) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, alerta.getDescricao());
            ps.setString(2, alerta.getDtHoraAlerta());
            ps.setString(3, alerta.getCaminhoArquivo());
            ps.setString(4, alerta.getTipoAlerta());  // Tipo de alerta (Pasta ou Arquivo)
            ps.setLong(5, computador.getId());
            int rowsAffected = ps.executeUpdate();

            textoAlerta = String.format("""
                        ALERTA!
                        Descrição: %s
                        Data e Hora: %s
                        Tipo de Alerta: %s
                        Computador: %s
                        """, alerta.getDescricao(), alerta.getDtHoraAlerta(), alerta.getTipoAlerta(), computador.getId());

            json.put("text", textoAlerta);
            Slack.sendMessage(json);

            return rowsAffected > 0; // Retorna verdadeiro se pelo menos uma linha for afetada
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false; // Em caso de falha
    }
}
