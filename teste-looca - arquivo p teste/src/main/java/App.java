import DataAcessObject.ComputadorDAO;
import DataAcessObject.StatusPcDAO;
import Entidades.*;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.util.Conversor;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class App {
    public static void main(String[] args) {
        // Fazer o login do usuário tambem. //// juhrs vai fazer
        System.out.println("""
                  ____ ____  _ ____    ___  ____ _  _    _  _ _ _  _ ___  ____   /
                  [__  |___  | |__|    |__] |___ |\\/| __ |  | | |\\ | |  \\ |  |  /\s
                  ___] |___ _| |  |    |__] |___ |  |     \\/  | | \\| |__/ |__| . \s
                                                                                 \s
                """);

        // objetos que foram criados na mão
        Looca looca = new Looca();
        Computador computador = new Computador();
        StatusPc idCaptura = new StatusPc();
        StatusPc memoriaUso = new StatusPc();
        StatusPc processadorUso = new StatusPc();
        StatusPc discoDisponivel = new StatusPc();
        StatusPc dtHoraCaptura = new StatusPc();


        // Objetos do looca
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



        computador.gerarTextoInicio();

            System.out.printf(String.format("""
             +==============================================================================+
             ||                         Informações da máquina                             ||
             +==============================================================================+
             ||                                                                            ||
             ||   Processador: %s                    ||
             ||   Sistema Operacional: %s                                             ||
             ||   Memória total: %s                                                  ||
             ||   Disco total: %s                                                   ||
             ||                                                                            ||
             +==============================================================================+
                """, nomeProcessador, sistemaOperacional,
                    Conversor.formatarBytes(memoriaTotal), Conversor.formatarBytes(discoTotal)));

        ComputadorDAO.cadastrarComputador(computador);
        ComputadorDAO.pegarIdComputador(computador);
        StatusPcDAO.pegarIdCaptura(idCaptura);
//
        Integer pontosMontagem = disco.getVolumes().size();
        long TEMPO = (2000);
        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                try {
                    LocalDateTime data = LocalDateTime.now();
                    dtHoraCaptura.setDtHoraCaptura(String.valueOf(data));

                    Long memoriaEmUso = memoria.getEmUso();
                    memoriaUso.setMemoriaUso(memoriaEmUso);

                    Double processadorEmUso = processador.getUso();
                    processadorUso.setProcessadorEmUso(processadorEmUso);

                    Long discoEmUso = disco.getVolumes().get(0).getDisponivel();
                    discoDisponivel.setDiscoDisponivel(discoEmUso);

                    StatusPcDAO.cadastrarCapturas(memoriaUso, processadorUso, discoDisponivel, dtHoraCaptura, computador);

                    if (disco.getVolumes().size() > pontosMontagem){
                        System.out.println("Algo de errado");
                    } else {
                        System.out.println("tudo certo");
                    }


                } catch (Exception e ){
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
    }
}
