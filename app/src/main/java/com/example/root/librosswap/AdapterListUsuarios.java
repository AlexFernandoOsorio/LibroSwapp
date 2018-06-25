package com.example.root.librosswap;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import java.util.List;

public class AdapterListUsuarios extends RecyclerView.Adapter<AdapterListUsuarios.MyViewHolder>{

    private List<UsersClass> listausers;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView dniUsuario,tipoUsuario,nombres,correo,celular,genero;
        public CheckBox checka;
        public CheckBox checks;
        public CheckBox checke;


        public MyViewHolder(View view) {
            super(view);
            nombres =  view.findViewById(R.id.textuser_nombre);
            correo =  view.findViewById(R.id.textuser_correo);
            dniUsuario =  view.findViewById(R.id.textuser_dni);
            celular= view.findViewById(R.id.textuser_celular);
            tipoUsuario= view.findViewById(R.id.textuser_tipou);
            genero= view.findViewById(R.id.textuser_genero);
            checka=view.findViewById(R.id.checkBox1);
            checks=view.findViewById(R.id.checkBox2);
            checke=view.findViewById(R.id.checkBox3);
        }
    }


    public AdapterListUsuarios(List<UsersClass> listaparameter) {

        this.listausers = listaparameter;
    }

    @Override
    public AdapterListUsuarios.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemslinear_users_adapter, parent, false);

        return new AdapterListUsuarios.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterListUsuarios.MyViewHolder holder, int position) {
        UsersClass usertemporal = listausers.get(position);
        holder.nombres.setText(usertemporal.getNombres()+usertemporal.getApellidos());
        holder.correo.setText(usertemporal.getCorreo());
        holder.dniUsuario.setText(usertemporal.getDniUsuario());
        holder.celular.setText(usertemporal.getCelular());
        holder.tipoUsuario.setText(usertemporal.getTipoUsuario());
        holder.genero.setText(usertemporal.getGenero());
        String check=usertemporal.getEstadoUsuario();
        /*switch (check)
        {
            case "1":
                break;
            case "0":
                break;
            case "-1":
                break;

        }*/

    }

    @Override
    public int getItemCount() {

        return listausers.size();
    }
}
