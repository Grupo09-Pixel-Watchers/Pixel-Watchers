import java.util.Timer;
import java.util.TimerTask;

public class Texto {
    Integer disco = 3;
    Double memoria = 4.8;
    Double cpu = 0.67;
    Integer cpuPorcent = 3;
    void exibirTextoLoginOk(){
        System.out.println("""
            *====================================================*
            ||                                                  ||
            ||          Login realizado com sucesso!            ||
            ||  Estamos puxando as informações do seu sistema   ||
            ||                                                  ||
            *====================================================*
            """);

        // puxando os dados mocados da máquina que está rodando
        long TEMPO = (1000 * 4); // chama o método a cada 4 segundos
        Timer timer = new Timer();
            TimerTask tarefa = new TimerTask() {

                public void run() {
                    try {
                        System.out.println("*======================================*");
                        System.out.println("||                                    ||");
                        System.out.printf("||   CPU: %d%%  %.2f GHz                ||\n",cpuPorcent+=1, cpu+= 0.4);
                        System.out.printf("||   Memória:  %.2f/7,9 GB            ||\n", memoria+= 0.2);
                        System.out.printf("||   Disco:  %d%% (SSD)                ||\n", disco+= 1);
                        System.out.println("||                                    ||");
                        System.out.println("*======================================*");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
        }

    void exibirTextoLoginFail(){
        System.out.println("""
            *====================================================*
            ||                                                  ||
            ||          Usuário ou senha incorretos             ||
            ||                Tente Novamente!                  ||
            ||                                                  ||
            *====================================================*
            """);
    }
}
