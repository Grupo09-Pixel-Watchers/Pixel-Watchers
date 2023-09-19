import DataAcessObject.ComputadorDAO;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.sistema.Sistema;
import entidades.Computador;

import java.util.Timer;
import java.util.TimerTask;

public class App {
    public static void main(String[] args) {
        Looca looca = new Looca(); // Objeto da API
        Computador computador = new Computador(); // Objeto da classe Computador.
        long tempo = (1000 * 2);

        // Objetos criados para obter os dados da máquina
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
        computador.setMemoria(memoriaLooca.getEmUso());

        ComputadorDAO.cadastrarComputador(computador);
        ComputadorDAO.pegarIdSistema(computador);

        System.out.println("Coputador cadastrado com sucesso!");
        long TEMPO = (1000 * 1);
        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("CPU: ");
                    System.out.println(computador.getMemoria());
                    ComputadorDAO.cadastrarMemoria(computador);

                } catch (Exception e ){
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
    }
}
