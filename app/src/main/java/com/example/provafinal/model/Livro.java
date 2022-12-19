package com.example.provafinal.model;

public class Livro {

    static int geratorId = -1;

    String titulo, autor, editora, isbm, ano;
    int id;

    public int getId() {
        return id;
    }

    public void setId( int id ){
        this.id = id;
    }

    public Livro(String titulo, String autor, String editora, String isbm, String ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.isbm = isbm;
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getIsbm() {
        return isbm;
    }

    public void setIsbm(String isbm) {
        this.isbm = isbm;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
