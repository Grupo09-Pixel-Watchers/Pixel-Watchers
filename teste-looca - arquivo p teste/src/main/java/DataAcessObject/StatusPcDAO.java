package DataAcessObject;

import Entidades.*;
import Conexao.Conexao;
import com.github.britooo.looca.api.util.Conversor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class StatusPcDAO {
    Integer idCaptura = 0;
    StatusPc statusPc = new StatusPc();

    public void exibirCapturas() {

    }
    public static String pegarIdCaptura (StatusPc statusPc){
        String sql = "SELECT idCaptura FROM status_pc";
        PreparedStatement ps = null;
        ResultSet rs = null; // ResultSet é uma classe utilizada para poder realizar os selects
        try{
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) { // o  next é para ele mover para a prox. linha
                statusPc.setIdCaptura(rs.getInt(1));
            }
            ps.execute();
        } catch (SQLException e ){
            e.printStackTrace();
        }
        return sql;
    }
    public static void cadastrarCapturas( StatusPc statusMemoria, StatusPc statusProcessador, StatusPc Disco,
                                         StatusPc dtHora, Computador computador) {
        String sql = "INSERT INTO status_pc " +
                "(memoriaUso, processadorUso, discoDisponivel, dtHoraCaptura, fkComputador) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setLong(1, statusMemoria.getMemoriaUso());
            ps.setDouble(2, statusProcessador.getProcessadorEmUso());
            ps.setDouble(3, Disco.getDiscoDisponivel());
            ps.setString(4, dtHora.getDtHoraCaptura());
            ps.setInt(5, computador.getId());
            ps.execute();

            String dataFormatadaa = dtHora.getDtHoraCaptura();
            Date dataFormater = new SimpleDateFormat("ddMMyyHHmmss").parse(dataFormatadaa);
            String dataFormatada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dataFormater);
            System.out.printf(String.format("""
                +================================================+
                ||               Dados da captura               ||
                +================================================+
                ||                                              ||      
                ||                registro: %d                  ||
                ||                                              ||
                ||              cpu em uso: %s                  ||
                ||            Memórria em uso: %s               ||
                ||           Disco Disponivel: %s               ||
                ||          data/hora da captura: %s            ||
                ||                                              ||
                +================================================+      
                
                """, dtHora.getIdCaptura(), // tenta pegar o ID aqui dog e arruma as formatações pls
                    statusProcessador.getProcessadorEmUso(),
                    Conversor.formatarBytes(statusMemoria.getMemoriaUso()),
                    Conversor.formatarBytes(Disco.getDiscoDisponivel()),
                    dataFormatada));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
