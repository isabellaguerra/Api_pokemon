package br.com.pokemon.service;

import br.com.pokemon.model.Pokemon;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ConsomeApi {

    private final String URL_BASE =
            "https://pokeapi.co/api/v2/pokemon/";

    // LISTAR POKEMONS
    public List<String> listarPokemons() {

        List<String> lista = new ArrayList<>();

        try {
            URL url = new URL(URL_BASE + "?limit=100");

            HttpURLConnection conexao =
                    (HttpURLConnection) url.openConnection();

            conexao.setRequestMethod("GET");

            BufferedReader leitor = new BufferedReader(
                    new InputStreamReader(
                            conexao.getInputStream()
                    )
            );

            String linha;
            StringBuilder resposta = new StringBuilder();

            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }

            leitor.close();

            JsonObject json =
                    JsonParser.parseString(resposta.toString())
                            .getAsJsonObject();

            JsonArray resultados =
                    json.getAsJsonArray("results");

            for (int i = 0; i < resultados.size(); i++) {

                JsonObject pokemon =
                        resultados.get(i).getAsJsonObject();

                String nome =
                        pokemon.get("name").getAsString();

                lista.add(nome);
            }

        } catch (Exception e) {
            System.out.println(
                    "Erro ao buscar Pokémons: " + e.getMessage()
            );
        }

        return lista;
    }


    // BUSCAR UM POKEMON ESPECÍFICO
    public Pokemon buscarPokemon(String nome) {

        Pokemon pokemon = new Pokemon();

        try {
            URL url = new URL(URL_BASE + nome.toLowerCase());

            HttpURLConnection conexao =
                    (HttpURLConnection) url.openConnection();

            conexao.setRequestMethod("GET");

            BufferedReader leitor = new BufferedReader(
                    new InputStreamReader(
                            conexao.getInputStream()
                    )
            );

            String linha;
            StringBuilder resposta = new StringBuilder();

            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }

            leitor.close();

            JsonObject json =
                    JsonParser.parseString(resposta.toString())
                            .getAsJsonObject();

            // ID
            pokemon.setId(
                    json.get("id").getAsInt()
            );

            // NOME
            pokemon.setNome(
                    json.get("name").getAsString()
            );

            // ALTURA
            pokemon.setAltura(
                    json.get("height").getAsInt()
            );

            // PESO
            pokemon.setPeso(
                    json.get("weight").getAsInt()
            );

            // TIPO
            JsonArray tipos =
                    json.getAsJsonArray("types");

            JsonObject primeiroTipo =
                    tipos.get(0).getAsJsonObject();

            String tipo =
                    primeiroTipo
                            .getAsJsonObject("type")
                            .get("name")
                            .getAsString();

            pokemon.setTipo(tipo);

        } catch (Exception e) {
            System.out.println(
                    "Pokémon não encontrado!"
            );

            return null;
        }

        return pokemon;
    }
}
