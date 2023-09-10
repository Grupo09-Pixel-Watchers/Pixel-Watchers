import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Texto textos = new Texto();
        Boolean validacao = false;

        System.out.println("""
                -----------------------------
                || BEM - VINDO AO VANGUARD ||
                -----------------------------
                """);

        // Enquanto a validação for falsa, repita a verificação
        while(!validacao){
            //Receber os dados do usuário
            System.out.print("Digite o login: ");
            String login = entrada.next();

            System.out.println();

            System.out.print("Digite a senha: ");
            String senha = entrada.next();

            // Se o login e a senha forem igual admin e 123, valida e libera o acesso
            if(login.equals("admin") && senha.equals("123")){
                textos.exibirTextoLoginOk();
                validacao = true;
            } else {
                textos.exibirTextoLoginFail();
            }
        }
    }
}