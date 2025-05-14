package br.com.alura.conversor;

import java.util.Scanner;

public class ConversorMoedas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RequestApi api = new RequestApi();
        GerarArquivoConsulta arquivoConsulta = new GerarArquivoConsulta();
        GerarArquivoLog logger = new GerarArquivoLog();

        String[][] opcoes = {
                {"USD", "BRL"},
                {"BRL", "USD"},
                {"EUR", "BRL"},
                {"BRL", "EUR"},
                {"USD", "EUR"},
                {"EUR", "USD"}
        };

        while (true) {
            System.out.println("\n******* CONVERSOR DE MOEDAS *******");
            for (int i = 0; i < opcoes.length; i++) {
                System.out.printf("%d - %s para %s\n", (i + 1), opcoes[i][0], opcoes[i][1]);
            }
            System.out.println("0 - Sair");
            System.out.print("--- Escolha uma opção: ");
            int escolha = scanner.nextInt();

            if (escolha == 0) break;
            if (escolha < 1 || escolha > 6) {
                System.out.println("Opção inválida.");
                continue;
            }

            String origem = opcoes[escolha - 1][0];
            String destino = opcoes[escolha - 1][1];

            System.out.print("Informe o valor em " + origem + ": ");
            double valor = scanner.nextDouble();

            try {
                double taxa = api.obterTaxaCambio(origem, destino);
                double convertido = valor * taxa;

                String resultado = String.format("%.2f %s = %.2f %s", valor, origem, convertido, destino);
                System.out.println("Resultado: " + resultado);

                arquivoConsulta.salvarConversao(resultado);
            } catch (Exception e) {
                System.out.println("Erro ao realizar conversão.");
                logger.registrarErro(e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Programa finalizado.");
    }
}