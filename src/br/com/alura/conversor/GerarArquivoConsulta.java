package br.com.alura.conversor;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class GerarArquivoConsulta {


    public void salvarConversao(String registro) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


        String dataHora = LocalDateTime.now().format(formatter);


        String linha = "[" + dataHora + "] " + registro;


        try (FileWriter writer = new FileWriter("conversoes.json", true)) {

            writer.write(linha + "\n");
            System.out.println("Conversão salva no arquivo.");
        } catch (IOException e) {

            System.out.println("Erro ao salvar conversão: " + e.getMessage());
        }
    }
}