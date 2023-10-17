import DataAcessObject.ComputadorDAO;
import DataAcessObject.StatusPcDAO;
import Entidades.Computador;
import Entidades.DiscoDisponivel;
import Entidades.MemoriaEmUso;
import Entidades.ProcessadorUso;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.processador.Processador;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class App {
    public static void main(String[] args) {
        Looca looca = new Looca();
        Computador computador = new Computador();
        MemoriaEmUso memoriaUso = new MemoriaEmUso(); // objeto da Memoria em Uso
        ProcessadorUso processadorUso = new ProcessadorUso();
        DiscoDisponivel discoDisponivel = new DiscoDisponivel();

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

        long TEMPO = (2000);
        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                try {
                    LocalDateTime data = LocalDateTime.now();
                    memoriaUso.setIdMemoria(String.valueOf(data));

                    Long memoriaEmUso = memoria.getEmUso();
                    memoriaUso.setMemoriaUso(memoriaEmUso);

                    Double processadorEmUso = processador.getUso();
                    processadorUso.setProcessadorEmUso(processadorEmUso);

                    Long discoEmUso = disco.getVolumes().get(0).getDisponivel();
                    discoDisponivel.setDiscoDisponivel(Double.valueOf(discoEmUso));

                    String pontoMontagem = disco.getVolumes().get(0).getPontoDeMontagem();
                    StatusPcDAO.cadastrarCapturas(memoriaUso, processadorUso, discoDisponivel, computador);

                    if (disco.getVolumes().size() == 1){
                        System.out.println("Tudo normal");
                    } else {
                        System.out.println("Alerta! \nDispositivo desconhecido conectado");
                    }

                } catch (Exception e ){
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
    }
}
