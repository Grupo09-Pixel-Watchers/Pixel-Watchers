package Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mensageiro {
    public static void generateLog(String log) throws IOException {
        // Formatação de data para contextualização do log
        Date dataHoraAtual = new Date();
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss - ");
        String dataHoraFormatada = dataFormatada.format(dataHoraAtual);

        // Caminho para o diretório onde serão armazenados os logs
        Path path = Paths.get("C:\\Users\\Aluno\\Desktop\\Arquivos Temporários\\testeLogs");

        // Cria o diretório caso não exista
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        // Caminho do arquivo que armazena os logs
        String caminhoDoArquivo = "C:\\Users\\Aluno\\Desktop\\Arquivos Temporários\\testeLogs\\log.txt";

        // Cria um objeto com o caminho do arquivo .txt que armazena os logs
        File logTeste = new File(caminhoDoArquivo);

        // Verifica o tamanho do arquivo
        if (logTeste.exists() && Files.size(Paths.get(caminhoDoArquivo)) > 2000) {
            // Se o arquivo atingiu 50 bytes, cria um novo arquivo
            int fileCount = 1;
            do {
                fileCount++;
                caminhoDoArquivo = "C:\\Users\\Aluno\\Desktop\\Arquivos Temporários\\testeLogs\\log" + fileCount + ".txt";
                logTeste = new File(caminhoDoArquivo);
            } while (logTeste.exists() && Files.size(Paths.get(caminhoDoArquivo)) > 2000);

            logTeste.createNewFile();
        }

        // Indica o arquivo em que deve ser escrito
        FileWriter fw = new FileWriter(logTeste, true);
        BufferedWriter bw = new BufferedWriter(fw);

        // Escreve o log no arquivo .txt
        bw.write(dataHoraFormatada + log);
        bw.newLine();

        // Limpa as variáveis para escrever um novo log no .txt
        bw.close();
        fw.close();
    }
}