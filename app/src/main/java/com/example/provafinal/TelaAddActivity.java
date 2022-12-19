package com.example.provafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.provafinal.controller.Codes;

public class TelaAddActivity extends AppCompatActivity {

    EditText ETtitulo, ETeditora, ETautor, ETisbm, ETano;
    Button btnAdd;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_add);

        id = -1;

        ETtitulo = findViewById(R.id.addTitulo);
        ETeditora = findViewById(R.id.addEditora);
        ETautor = findViewById(R.id.addAutor);
        ETano = findViewById(R.id.addAno);
        ETisbm = findViewById(R.id.addIsbm);
        btnAdd = findViewById(R.id.buttonAdicionar);

        if(getIntent().getExtras() != null ) {
            String titulo = getIntent().getExtras().getString(Codes.CHAVE_TITULO);
            String editora = getIntent().getExtras().getString(Codes.CHAVE_EDITORA);
            String autor = getIntent().getExtras().getString(Codes.CHAVE_AUTOR);
            String ano = getIntent().getExtras().getString(Codes.CHAVE_ANO);
            String isbm = getIntent().getExtras().getString(Codes.CHAVE_ISBM);
            String idString = getIntent().getExtras().getString(Codes.CHAVE_ID);

            if(idString != null) {
                id = Integer.parseInt( idString );
                Toast.makeText( this, "Id: " + id, Toast.LENGTH_LONG ).show();
            }

            ETtitulo.setText(titulo);
            ETeditora.setText(editora);
            ETautor.setText(autor);
            ETano.setText(ano);
            ETisbm.setText(isbm);
        }
    }


    public void addbtnCancelar(View view) {
        finish();
    }

    public void addAdicionar(View view) {
        String titulo = ETtitulo.getText().toString();
        String editora = ETeditora.getText().toString();
        String autor = ETautor.getText().toString();
        String ano = ETano.getText().toString();
        String isbm = ETisbm.getText().toString();

        Intent intent = new Intent();
        intent.putExtra( Codes.CHAVE_TITULO, titulo );
        intent.putExtra( Codes.CHAVE_EDITORA, editora );
        intent.putExtra( Codes.CHAVE_AUTOR, autor );
        intent.putExtra( Codes.CHAVE_ANO, ano );
        intent.putExtra( Codes.CHAVE_ISBM, isbm );

        if(id >= 0) intent.putExtra(Codes.CHAVE_ID, ""+id);

        setResult(Codes.RESPONSE_OK, intent);
        finish();
    }
}