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
    private boolean curtida, descurtida;

    public Musica(String nome, String genero, Artista autor, boolean curtida, boolean descurtida) {
        this.nome = nome;
        this.genero = genero;
        this.autor = autor;
        this.curtida = curtida;
        this.descurtida = descurtida;
    }   
}
