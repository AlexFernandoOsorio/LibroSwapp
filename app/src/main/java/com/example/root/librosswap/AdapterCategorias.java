package com.example.root.librosswap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.util.List;
public class AdapterCategorias extends RecyclerView.Adapter<AdapterCategorias.MyViewHolder>{
    private List<CategoClass> listcategorias;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView idcategoria, nombrecategoria;
        public Button modif,elimi;


        public MyViewHolder(View view) {
            super(view);
            idcategoria =  view.findViewById(R.id.txt_idcat);
            nombrecategoria =  view.findViewById(R.id.txt_nombrecat);
            modif=view.findViewById(R.id.but_catmodif);
            elimi=view.findViewById(R.id.but_catelim);
        }
    }


    public AdapterCategorias(List<CategoClass> listaparameter) {

        this.listcategorias = listaparameter;
    }

    @Override
    public AdapterCategorias.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemslinear_cat_adapter, parent, false);

        return new AdapterCategorias.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterCategorias.MyViewHolder holder, int position) {
        CategoClass cattemporal = listcategorias.get(position);
        holder.idcategoria.setText(cattemporal.getIdCategoria());
        holder.nombrecategoria.setText(cattemporal.getNombreCategoria());

    }

    @Override
    public int getItemCount() {

        return listcategorias.size();
    }
}
