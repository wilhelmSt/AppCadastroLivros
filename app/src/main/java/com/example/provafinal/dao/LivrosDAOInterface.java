package com.example.provafinal.dao;

import android.content.Context;

import com.example.provafinal.model.Livro;

import java.util.ArrayList;

public interface LivrosDAOInterface {

    static LivrosDAOInterface getInstance(Context context) {
        return null;
    }

    boolean addLivro( Livro c );
    boolean editLivro( Livro c );
    Livro getLivro( int livroId );
    ArrayList<Livro> getListLivros();

    boolean init();
    boolean close();
    boolean isStarted();
}
