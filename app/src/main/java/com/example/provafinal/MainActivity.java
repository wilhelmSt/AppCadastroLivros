package com.example.provafinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.provafinal.controller.Codes;
import com.example.provafinal.dao.LivrosDAOInterface;
import com.example.provafinal.dao.LivrosDAOSharedPreferences;
import com.example.provafinal.model.Livro;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Livro> lista;

    int posicao;

    CustomAdapter adapter;
    RecyclerView recyclerView;

    LivrosDAOInterface livrosDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        livrosDAO = LivrosDAOSharedPreferences.getInstance( this );
        livrosDAO.init();
        posicao = -1;

        lista = livrosDAO.getListLivros();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this );

        adapter = new CustomAdapter( this, lista );
        recyclerView = findViewById( R.id.listaLivros );
        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setAdapter( adapter );

        recyclerView.addItemDecoration( new DividerItemDecoration( this, DividerItemDecoration.VERTICAL ) );
    }

    protected void onStop() {
        super.onStop();
        livrosDAO.close();
    }

    public void adicionarLivros(View view ) {

        Intent intent = new Intent(this, TelaAddActivity.class);
        startActivityForResult(intent, Codes.REQUEST_ADD);
    }

    public void editarLivro( int pos ){

        Livro livro = lista.get( pos );

        String titulo = livro.getTitulo();
        String editora = livro.getEditora();
        String autor = livro.getAutor();
        String ano = livro.getAno();
        String isbm = livro.getIsbm();
        int id = livro.getId();

        Intent intent = new Intent( this, TelaAddActivity.class );
        intent.putExtra( Codes.CHAVE_TITULO, titulo );
        intent.putExtra( Codes.CHAVE_EDITORA, editora );
        intent.putExtra( Codes.CHAVE_AUTOR, autor );
        intent.putExtra( Codes.CHAVE_ANO, ano );
        intent.putExtra( Codes.CHAVE_ISBM, isbm );
        intent.putExtra( Codes.CHAVE_ID, ""+id );

        startActivityForResult( intent, Codes.REQUEST_EDT );
    }

    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == Codes.REQUEST_ADD && resultCode == Codes.RESPONSE_OK ){

            String titulo = data.getExtras().getString( Codes.CHAVE_TITULO );
            String editora = data.getExtras().getString( Codes.CHAVE_EDITORA );
            String autor = data.getExtras().getString( Codes.CHAVE_AUTOR );
            String ano = data.getExtras().getString( Codes.CHAVE_ANO );
            String isbm = data.getExtras().getString( Codes.CHAVE_ISBM );

            Livro livro = new Livro( titulo, autor, editora, isbm, ano );

            livrosDAO.addLivro( livro );
            adapter.notifyDataSetChanged();

        } else if( requestCode == Codes.REQUEST_EDT && resultCode == Codes.RESPONSE_OK ){

            String titulo = data.getExtras().getString( Codes.CHAVE_TITULO );
            String editora = data.getExtras().getString( Codes.CHAVE_EDITORA );
            String autor = data.getExtras().getString( Codes.CHAVE_AUTOR );
            String ano = data.getExtras().getString( Codes.CHAVE_ANO );
            String isbm = data.getExtras().getString( Codes.CHAVE_ISBM );
            String idString = data.getExtras().getString( Codes.CHAVE_ID );

            int id = -1;
            if( idString != null ){
                id = Integer.parseInt( idString );
                Toast.makeText( this, "ID: " + id, Toast.LENGTH_LONG ).show();

                Livro livro = new Livro( titulo, autor, editora, isbm, ano );
                livro.setId( id );

                livrosDAO.editLivro( livro );
                adapter.notifyDataSetChanged();

            }

            adapter.notifyDataSetChanged();
        }
    }
}