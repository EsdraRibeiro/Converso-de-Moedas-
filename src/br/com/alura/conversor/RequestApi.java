package br.com.alura.conversor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestApi {

       public double obterTaxaCambio(String moedaOrigem, String moedaDestino) throws Exception {
       String apiKey = "cc79b1fb3abaae2c8d73ba41";

        String endereco = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + moedaOrigem;

        URL url = new URL(endereco);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));


        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
        reader.close();


        JsonObject rates = json.getAsJsonObject("conversion_rates");


        return rates.get(moedaDestino).getAsDouble();
    }
}