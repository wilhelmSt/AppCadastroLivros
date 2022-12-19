package com.example.provafinal.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.example.provafinal.controller.Codes;
import com.example.provafinal.model.Livro;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LivrosDAOSharedPreferences implements LivrosDAOInterface {

    private static ArrayList<Livro> lista;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static Context context;
    private static boolean initialized = false;

    private static LivrosDAOSharedPreferences livrosDAOSharedPreferences = null;

    private LivrosDAOSharedPreferences( Context c ){
        LivrosDAOSharedPreferences.context = c;
    }

    public static LivrosDAOInterface getInstance(Context context) {

        if( livrosDAOSharedPreferences == null ){
            livrosDAOSharedPreferences = new LivrosDAOSharedPreferences( context );
        }
        return livrosDAOSharedPreferences;
    }

    @Override
    public boolean addLivro(Livro c) {
        lista.add( c );
        return true;
    }

    @Override
    public boolean editLivro( Livro c ) {

        boolean edited = false;

        for( Livro livro : lista ){

            if( livro.getId() == c.getId() ){
                livro.setTitulo( c.getTitulo() );
                livro.setEditora( c.getEditora() );
                livro.setAutor( c.getAutor() );
                livro.setAno( c.getAno() );
                livro.setIsbm( c.getIsbm() );

                edited = true;
                break;

            }
        }
        return edited;
    }

    @Override
    public Livro getLivro( int livroId ) {

        Livro livroBuscar = null;
        for( Livro livro : lista ){
            if( livro.getId() == livroId ){
                livroBuscar = livro;
                break;
            }
        }

        return livroBuscar;
    }

    @Override
    public ArrayList<Livro> getListLivros() {
        return lista;
    }

    @Override
    public boolean init() {

        sharedPreferences = context.getSharedPreferences(Codes.SHAREDPREFERENCES_FILE,
                Context.MODE_PRIVATE );
        editor = sharedPreferences.edit();

        String listaLivrosString = sharedPreferences.getString( Codes.SHAREDPREFERENCES_FILE_KEY, "" );

        if( listaLivrosString.equals( "" ) ) lista = new ArrayList<Livro>();
        else{

            Type listType = TypeToken.getParameterized(ArrayList.class, Livro.class).getType();
            Gson gson = new Gson();
            lista = gson.fromJson( listaLivrosString, listType );

        }

        initialized = true;

        return true;
    }

    public boolean isStarted(){
        return initialized;
    }

    @Override
    public boolean close() {

        Gson gson = new Gson();
        String listaLivrosString = gson.toJson( lista );
        editor.putString( Codes.SHAREDPREFERENCES_FILE_KEY, listaLivrosString );
        editor.apply();

        return true;
    }
}
