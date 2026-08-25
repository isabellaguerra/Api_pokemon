package br.com.pokemon.model;

public class Pokemon {

    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String tipo;

    // Construtor vazio
    public Pokemon() {
    }

    // Construtor completo
    public Pokemon(int id, String nome, int altura, int peso, String tipo) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.tipo = tipo;
    }

    // GETTERS E SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "\n--- POKEMON ---" +
                "\nID: " + id +
                "\nNome: " + nome +
                "\nAltura: " + altura +
                "\nPeso: " + peso +
                "\nTipo: " + tipo;
    }
}
