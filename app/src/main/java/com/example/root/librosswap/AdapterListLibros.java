package com.example.root.librosswap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class AdapterListLibros extends RecyclerView.Adapter<AdapterListLibros.MyViewHolder>{

    private List<LibrosClass> listalibros;

    private Context context;
    AlertDialog Builder;

    Dialog dialogsubida;

    private String idLibro;
    private String titulols;
    private String autordils;
    private String lbsmls;
    private String edicionls;
    private String aniopublils;
    private String editorialls;
    private String descripls;
    private String ubicacionls;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView coduser, titulo,autor,estado;
        public ImageView portada;
        public Button butmod,buteli;

        public MyViewHolder(View view) {
            super(view);
            coduser =  view.findViewById(R.id.txt_coduserl);
            titulo =  view.findViewById(R.id.txt_cardtitulol);
            autor =  view.findViewById(R.id.txt_cardautorl);
            estado =  view.findViewById(R.id.txt_cardestadolibrol);
            portada= view.findViewById(R.id.img_cardportadal);
            butmod=view.findViewById(R.id.but_listlimodificar);
            buteli=view.findViewById(R.id.but_listlieliminar);;
        }
    }

    public AdapterListLibros(List<LibrosClass> listaparameter) {

        this.listalibros = listaparameter;
    }

    @Override
    public AdapterListLibros.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemslinear_libros_adapter, parent, false);
        context=parent.getContext();
        return new AdapterListLibros.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterListLibros.MyViewHolder holder, int position) {
        LibrosClass librotemporal = listalibros.get(position);
        holder.coduser.setText("Propietario: "+librotemporal.getIdLSBM());
        holder.titulo.setText(librotemporal.getTitulo());
        holder.autor.setText(librotemporal.getAutor());
        holder.estado.setText(librotemporal.getEstadoLibr());
        idLibro=librotemporal.getIdLibros();
        Glide.with(holder.portada).load(librotemporal.getPortada()).into(holder.portada);

        holder.butmod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View promptView = LayoutInflater.from(context).inflate(R.layout.dialog_modificalibros, null);
                Builder = new AlertDialog.Builder(v.getContext()).create();
                final EditText titulol=promptView.findViewById(R.id.diallm_titulo);
                final EditText autordil=promptView.findViewById(R.id.diallm_autor);
                final EditText lbsml=promptView.findViewById(R.id.diallm_lsbm);
                final EditText edicionl=promptView.findViewById(R.id.diallm_lsbm);
                final EditText aniopublil=promptView.findViewById(R.id.diallm_añopubl);
                final EditText editoriall=promptView.findViewById(R.id.diallm_editorial);
                final EditText descripl=promptView.findViewById(R.id.diallm_descripcion);
                final EditText ubicacionl=promptView.findViewById(R.id.diallm_ubicacion);
                Button btnagregar = (Button) promptView.findViewById(R.id.btn_dialogbusbm);
                Button btnsalir = (Button) promptView.findViewById(R.id.btn_dialogbussm);
                btnagregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        titulols=titulol.getText().toString();
                        autordils=autordil.getText().toString();
                        lbsmls=lbsml.getText().toString();
                        edicionls=edicionl.getText().toString();
                        aniopublils=aniopublil.getText().toString();
                        editorialls=editoriall.getText().toString();
                        descripls=descripl.getText().toString();
                        ubicacionls=ubicacionl.getText().toString();

                        titulol.setError(null);
                        autordil.setError(null);
                        lbsml.setError(null);
                        edicionl.setError(null);
                        aniopublil.setError(null);
                        editoriall.setError(null);
                        descripl.setError(null);
                        ubicacionl.setError(null);

                        View focusView = null;

                        if (!Boolean.valueOf(new InternetConnection(context.getApplicationContext()).isConnectingInternet()).booleanValue())
                        {
                            Snackbar.make(v, "Sin Conexion a Internet", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();;
                        }
                        else{
                            if (TextUtils.isEmpty(titulols)
                                    ||TextUtils.isEmpty(autordils)
                                    ||TextUtils.isEmpty(descripls)
                                    ||TextUtils.isEmpty(ubicacionls)
                                    ||TextUtils.isEmpty(editorialls))
                            {
                                //dialog.dismiss();
                                Snackbar.make(v, "Asegurese de llenar los campos Titulo, Autor, Descripción, Editorial", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                            }
                            else {
                                if (!TextUtils.isEmpty(lbsmls)) {
                                    if (lbsmls.length() < 11 || lbsmls.length() > 13) {
                                        lbsml.setError("El ISBN necesita tener 11 o 13 digitos");
                                        focusView = lbsml;
                                        focusView.requestFocus();
                                    } else {
                                        uploadSelectedImageToServer();
                                    }
                                }
                                if (!TextUtils.isEmpty(aniopublils)) {
                                    if (aniopublils.length()!=4) {
                                        lbsml.setError("El Año de Publicacion debe ser valido");
                                        focusView = aniopublil;
                                        focusView.requestFocus();
                                    } else {
                                        uploadSelectedImageToServer();
                                    }
                                }
                            }
                        }
                    }
                });
                btnsalir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Builder.dismiss();
                    }
                });
                Builder.setView(promptView);
                Builder.show();

            }
        });
        holder.buteli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {

        return listalibros.size();
    }

    public void uploadSelectedImageToServer()
    {
        RequestQueue queue = Volley.newRequestQueue(context);

        dialogsubida = new Dialog(context);
        dialogsubida.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogsubida.setContentView(R.layout.dialog_loading);
        dialogsubida.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.InsertLibros,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        dialogsubida.dismiss();
                        Toast.makeText(context, "Modificado Correctamente" , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        dialogsubida.dismiss();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new Hashtable<String, String>();

                params.put("idISBN",lbsmls);
                params.put("titulo",titulols);
                params.put("autor",autordils);
                params.put("edicion",edicionls);
                params.put("anioPublicacion",aniopublils);
                params.put("editorial",editorialls);
                params.put("descripcion",descripls);
                params.put("ubicacion",ubicacionls);
                params.put("idLibro",idLibro);

                return params;
            }
        };
        queue.add(stringRequest);
    }

}

