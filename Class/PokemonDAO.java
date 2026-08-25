package br.com.pokemon.dao;

import br.com.pokemon.connection.ConnectionFactory;
import br.com.pokemon.model.Pokemon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAO {

    // SALVAR FAVORITO
    public void salvar(Pokemon pokemon) {

        String sql =
                "INSERT INTO favoritos " +
                "(pokemon_id, nome, altura, peso, tipo) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conexao =
                        ConnectionFactory.getConnection();

                PreparedStatement comando =
                        conexao.prepareStatement(sql)
        ) {

            comando.setInt(
                    1,
                    pokemon.getId()
            );

            comando.setString(
                    2,
                    pokemon.getNome()
            );

            comando.setInt(
                    3,
                    pokemon.getAltura()
            );

            comando.setInt(
                    4,
                    pokemon.getPeso()
            );

            comando.setString(
                    5,
                    pokemon.getTipo()
            );

            comando.executeUpdate();

            System.out.println(
                    "Pokémon salvo nos favoritos!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Erro ao salvar: "
                            + e.getMessage()
            );
        }
    }


    // LISTAR FAVORITOS
    public List<Pokemon> listarFavoritos() {

        List<Pokemon> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM favoritos";

        try (
                Connection conexao =
                        ConnectionFactory.getConnection();

                PreparedStatement comando =
                        conexao.prepareStatement(sql);

                ResultSet resultado =
                        comando.executeQuery()
        ) {

            while (resultado.next()) {

                Pokemon pokemon =
                        new Pokemon();

                pokemon.setId(
                        resultado.getInt(
                                "pokemon_id"
                        )
                );

                pokemon.setNome(
                        resultado.getString(
                                "nome"
                        )
                );

                pokemon.setAltura(
                        resultado.getInt(
                                "altura"
                        )
                );

                pokemon.setPeso(
                        resultado.getInt(
                                "peso"
                        )
                );

                pokemon.setTipo(
                        resultado.getString(
                                "tipo"
                        )
                );

                lista.add(pokemon);
            }

        } catch (Exception e) {

            System.out.println(
                    "Erro ao listar: "
                            + e.getMessage()
            );
        }

        return lista;
    }


    // EXCLUIR FAVORITO
    public void excluir(int pokemonId) {

        String sql =
                "DELETE FROM favoritos " +
                "WHERE pokemon_id = ?";

        try (
                Connection conexao =
                        ConnectionFactory.getConnection();

                PreparedStatement comando =
                        conexao.prepareStatement(sql)
        ) {

            comando.setInt(
                    1,
                    pokemonId
            );

            int linhas =
                    comando.executeUpdate();

            if (linhas > 0) {

                System.out.println(
                        "Pokémon excluído dos favoritos!"
                );

            } else {

                System.out.println(
                        "Pokémon não encontrado!"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Erro ao excluir: "
                            + e.getMessage()
            );
        }
    }
}
