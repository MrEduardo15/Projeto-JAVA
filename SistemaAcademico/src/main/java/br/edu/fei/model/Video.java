/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.model;

/**
 *
 * @author Pichau
 */
public class Video {
    private String titulo;

    // Construtor
    public Video(String titulo) {
        this.titulo = titulo;
    }

    // Método para pegar o título
    public String getTitulo() {
        return titulo;
    }
}
