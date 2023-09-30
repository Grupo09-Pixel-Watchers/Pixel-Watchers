import DataAcessObject.ComputadorDAO;
import DataAcessObject.MemoriaDAO;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.util.Conversor;
import entidades.Computador;
import entidades.MemoriaEmUso;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class App {
    public static void main(String[] args) {
        Looca looca = new Looca();
        Computador computador = new Computador();
        MemoriaEmUso mem = new MemoriaEmUso();

        Sistema sistema = looca.getSistema();
        Memoria memoriaLooca = looca.getMemoria();

        // Armazenando as informações do SO usando o metodo sistema.getSistemaOperacional()
        String sistemaOperacional = sistema.getSistemaOperacional();

        // Armazenando as informações do fabricante usando o metodo sistema.getFabricante()
        String fabricante = sistema.getFabricante();

        // Armazenando a arquitetura do SO usando o metodo sistema.getArquitetura()
        Integer arquitetura = sistema.getArquitetura();

        // Armazenando a data que foi inicializado usando o metodo sistema.getinicializado()
        String dtInicializado = String.valueOf(sistema.getInicializado());

        computador.setSO(sistemaOperacional);
        computador.setFabricante(fabricante);
        computador.setArquitetura(arquitetura);
        computador.setDtInicializado(dtInicializado);


        ComputadorDAO.cadastrarComputador(computador);
        ComputadorDAO.pegarIdSistema(computador);

        System.out.println("Computador cadastrado com sucesso!");
        System.out.printf("""
                Informações do sistema da máquina:
                SO: %s
                Fabricante: %s
                Arquitetura: %s
                Data Inicializado: %s
                """, sistemaOperacional, fabricante, arquitetura, dtInicializado);

        long TEMPO = (1000 * 1);
        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                try {
                    // Ver pq que nnnão cadastra se tiver na classe MemoriaDAO
                    LocalDateTime data = LocalDateTime.now();
                    mem.setIdMemoria(String.valueOf(data));
//                    MemoriaDAO.pegarDtCaptura(data);

                    // Ver pq que não cadastra se tiver na classe MemoriaDAO tambem
                    Long memoria = memoriaLooca.getEmUso();
//                    MemoriaDAO.pegarMemoriaEmUso(memoria);
                    mem.setMemoriaUso(memoria);

                    System.out.println("Memória: "+Conversor.formatarBytes(memoria));
                    MemoriaDAO.cadastrarMemoriaEmUso(mem, computador);
                } catch (Exception e ){
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
    }
}
