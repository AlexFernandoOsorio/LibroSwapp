package com.example.root.librosswap;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterListLibros extends RecyclerView.Adapter<AdapterListLibros.MyViewHolder>{

    private List<LibrosClass> listalibros;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView coduser, titulo,autor,estado;
        public ImageView portada;

        public MyViewHolder(View view) {
            super(view);
            coduser =  view.findViewById(R.id.txt_coduserl);
            titulo =  view.findViewById(R.id.txt_cardtitulol);
            autor =  view.findViewById(R.id.txt_cardautorl);
            estado =  view.findViewById(R.id.txt_cardestadolibrol);
            portada= view.findViewById(R.id.img_cardportadal);
        }
    }


    public AdapterListLibros(List<LibrosClass> listaparameter) {

        this.listalibros = listaparameter;
    }

    @Override
    public AdapterListLibros.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemslinear_libros_adapter, parent, false);

        return new AdapterListLibros.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterListLibros.MyViewHolder holder, int position) {
        LibrosClass librotemporal = listalibros.get(position);
        holder.coduser.setText("Propietario: "+librotemporal.getIdLSBM());
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

