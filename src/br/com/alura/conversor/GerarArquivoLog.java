package br.com.alura.conversor;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;


public class GerarArquivoLog {


    public void registrarErro(String mensagem) {

        try (FileWriter writer = new FileWriter("log.txt", true)) {
            writer.write(LocalDateTime.now() + " - ERRO: " + mensagem + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao registrar log.");
        }
    }
}