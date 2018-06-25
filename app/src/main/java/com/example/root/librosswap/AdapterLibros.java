package com.example.root.librosswap;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterLibros extends RecyclerView.Adapter<AdapterLibros.MyViewHolder> {

    private List<LibrosClass> listalibros;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lsbm, titulo,autor,estado;
        public ImageView portada;

        public MyViewHolder(View view) {
            super(view);
            lsbm =  view.findViewById(R.id.txt_cardlsbm);
            titulo =  view.findViewById(R.id.txt_cardtitulo);
            autor =  view.findViewById(R.id.txt_cardautor);
            estado =  view.findViewById(R.id.txt_cardestadolibro);
            portada= view.findViewById(R.id.img_cardportada);
        }
    }


    public AdapterLibros(List<LibrosClass> listaparameter) {

        this.listalibros = listaparameter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemscard_libros_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LibrosClass librotemporal = listalibros.get(position);
        holder.lsbm.setText(librotemporal.getIdLSBM());
        holder.titulo.setText(librotemporal.getTitulo());
        holder.autor.setText(librotemporal.getAutor());
        holder.estado.setText(librotemporal.getEstadoLibr());
        Glide.with(holder.portada).load(librotemporal.getPortada()).into(holder.portada);

    }

    @Override
    public int getItemCount() {

        return listalibros.size();
    }
}
