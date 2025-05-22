/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author unifmkuniyoshi
 */
public class Musica {
    private String nome, genero;
    private Artista autor;
    private int curtida, descurtida;

    public Musica(String nome, String genero, Artista autor) {
        this.nome = nome;
        this.genero = genero;
        this.autor = autor;
    }

    public String getMusicaNome() {
        return nome;
    }

    public void setMusicaNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Artista getAutor() {
        return autor;
    }

    public void setAutor(Artista autor) {
        this.autor = autor;
    }
    
    
}
