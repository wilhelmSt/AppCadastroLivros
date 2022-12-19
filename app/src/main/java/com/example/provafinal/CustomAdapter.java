package com.example.provafinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.provafinal.model.Livro;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    ArrayList<Livro> dataSet;
    MainActivity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView TextViewLivrosTitle, TextViewLivrosEditora,
                TextViewLivrosAutor, TextViewLivrosAno,
                TextViewLivrosIsbm;

        ImageView imgEdit;

        public ViewHolder( View itemView ) {
            super(itemView);

            TextViewLivrosTitle = itemView.findViewById(R.id.TextViewLivrosTitle);
            TextViewLivrosEditora = itemView.findViewById(R.id.TextViewLivrosEditora);
            TextViewLivrosAutor = itemView.findViewById(R.id.TextViewLivrosAutor);
            TextViewLivrosAno = itemView.findViewById(R.id.TextViewLivrosAno);
            TextViewLivrosIsbm = itemView.findViewById(R.id.TextViewLivrosIsbm);
            imgEdit = itemView.findViewById(R.id.imgEdit);
        }

        public TextView getTextViewLivrosTitle() {
            return TextViewLivrosTitle;
        }

        public TextView getTextViewLivrosEditora() {
            return TextViewLivrosEditora;
        }

        public TextView getTextViewLivrosAutor() {
            return TextViewLivrosAutor;
        }

        public TextView getTextViewLivrosAno() {
            return TextViewLivrosAno;
        }

        public TextView getTextViewLivrosIsbm() {
            return TextViewLivrosIsbm;
        }

        public ImageView getImgEdit() {
            return imgEdit;
        }
    }

    public CustomAdapter( MainActivity activity, ArrayList<Livro> data ){
        this.dataSet = data;
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext() ).inflate(
                R.layout.livros_layout, parent, false
        );

        return new ViewHolder( view );
    }

    public void onBindViewHolder( ViewHolder holder, int position ) {

        Livro livro = dataSet.get( position );

        holder.getTextViewLivrosTitle().setText( livro.getTitulo() );
        holder.getTextViewLivrosEditora().setText( livro.getEditora() );
        holder.getTextViewLivrosAutor().setText( livro.getAutor() );
        holder.getTextViewLivrosAno().setText( livro.getAno() );
        holder.getTextViewLivrosIsbm().setText( livro.getIsbm() );

        holder.getImgEdit().setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View view) {
                        activity.editarLivro( holder.getAdapterPosition() );
                    }

                });
    }

    public int getItemCount() {
        return dataSet.size();
    }
}
