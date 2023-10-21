import DataAcessObject.AlertaDAO;
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

import java.io.File;
import java.util.Arrays;
import java.util.List;

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
        Alerta alerta = new Alerta();



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
             ||   Processador: %s                                                          ||
             ||   Sistema Operacional: %s                                                  ||
             ||   Memória total: %s                                                        ||
             ||   Disco total: %s                                                          ||
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
                        System.out.println("ATENÇÃO!\nDISCO DESCONHECIDO CONECTADO ");
                    } else {
                        System.out.println("QUANTIDADE DE DISCOS ESTÁ DE ACORDO :)");
                    }


                } catch (Exception e ){
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);


        LocalDateTime data = LocalDateTime.now();

        System.out.println("COMEÇOU O PROCESSO DE VERIFICAÇÃO DE ARQUIVOS E PASTAS PROIBIDOS");

        // Caminho da raiz do disco (pode variar dependendo do sistema operacional)
        File rootDirectory = new File("C:\\");

        // Lista de programas proibidos (pastas)
        List<String> folderBlacklist = Arrays.asList("HxD", "The Cheat", "CoSMOS", "WeMod", "Squalr", "TestSign", "KDMapper", "Windows API", "ArtMoney", "Cheat Engine");

        // Lista de programas proibidos (arquivos)
        List<String> fileBlacklist = Arrays.asList("Squalr.exe", "ArtMoney.exe", "Cheat Engine.exe", "HxD.exe", "CoSMOS.exe", "WeMod.exe");


        try {
            findBlacklistedAppsInDirectory(rootDirectory, folderBlacklist, fileBlacklist);
            // Se chegou aqui, nenhum programa proibido foi encontrado
            System.out.println("A máquina está segura para uso.");

        } catch (ProgramProibidoEncontradoException e) {
            System.out.println(e.getMessage());
            alerta.setDtHoraAlerta(String.valueOf(data));
            alerta.setDescricao("Arquivo suspeito encontrado");
            AlertaDAO.cadastrarAlerta(alerta, computador);
        }
    }

    public static void findBlacklistedAppsInDirectory(File directory, List<String> folderBlacklist, List<String> fileBlacklist) throws ProgramProibidoEncontradoException {
        if (directory.exists() && directory.isDirectory()) {
            // Lista de arquivos e pastas no diretório
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory() && folderBlacklist.contains(file.getName())) {
                        // Se a pasta estiver na lista de programas proibidos, lançamos uma exceção
                        throw new ProgramProibidoEncontradoException("Pasta proibida encontrada: " + file.getAbsolutePath());

                    }
                    if (file.isFile() && fileBlacklist.contains(file.getName())) {
                        // Se o arquivo estiver na lista de programas proibidos, lançamos uma exceção
                        throw new ProgramProibidoEncontradoException("Arquivo proibido encontrado: " + file.getAbsolutePath());
                    }
                }
                for (File subDirectory : files) {
                    if (subDirectory.isDirectory()) {
                        // Verifica subdiretórios de forma recursiva
                        findBlacklistedAppsInDirectory(subDirectory, folderBlacklist, fileBlacklist);
                    }
                }
            }
        }
    }

    public static class ProgramProibidoEncontradoException extends Exception {
        // Exceção personalizada para indicar que um programa proibido foi encontrado
        public ProgramProibidoEncontradoException(String message) {
            super(message);
        }
    }
}
