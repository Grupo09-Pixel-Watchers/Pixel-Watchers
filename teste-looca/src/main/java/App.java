import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import dataAcessObject.ComputadorDAO;
import dataAcessObject.MemoriaDAO;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import entidades.Computador;
import entidades.MemoriaEmUso;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class App {
    public static void main(String[] args) {
        Looca looca = new Looca();
        Computador computador = new Computador();
        MemoriaEmUso memoriaUso = new MemoriaEmUso(); // objeto da Memoria em Uso

        Memoria memoria = looca.getMemoria();
        Processador processador = looca.getProcessador();
        DiscoGrupo disco = looca.getGrupoDeDiscos();
        Sistema sistema = looca.getSistema();

        // Variáveis que guardam as informações para o cadastro
        String nomeProcessador = processador.getNome();
        computador.setProcessador(nomeProcessador);

        String sistemaOperacional = sistema.getSistemaOperacional();
        computador.setSO(sistemaOperacional);


        Long memoriaTotal = (memoria.getTotal());
        computador.setMemoriaTot(memoriaTotal);

        Long discoTotal = (disco.getTamanhoTotal());
        computador.setDiscoTotal((discoTotal));


        ComputadorDAO.cadastrarComputador(computador);
        ComputadorDAO.pegarIdComputador(computador);

        System.out.println("Computador cadastrado com sucesso!");
        System.out.printf("""
                Informações da máquina:
                Processador: %s
                Sistema Operacional: %s
                Memória total: %d
                Disco total: %s
                """, nomeProcessador, sistemaOperacional, memoriaTotal, discoTotal);

        long TEMPO = (1500);
        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                try {
                    // Ver pq que não cadastra se tiver na classe MemoriaDAO
                    LocalDateTime data = LocalDateTime.now();
                    memoriaUso.setIdMemoria(String.valueOf(data));
////                    MemoriaDAO.pegarDtCaptura(data);
//
//                    // Ver pq que não cadastra se tiver na classe MemoriaDAO tambem
////                   MemoriaDAO.pegarMemoriaEmUso(memoria);
                    Long memoriaEmUso = memoria.getEmUso();
                    memoriaUso.setMemoriaUso(memoriaEmUso);

                    Double processadorEmUso = processador.getUso();
                    memoriaUso.setProcessadorUso(processadorEmUso);
//                    System.out.println("Memória: "+memoriaUso.getMemoriaUso());
                    MemoriaDAO.cadastrarMemoriaEmUso(memoriaUso, computador);
                } catch (Exception e ){
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
    }
}
