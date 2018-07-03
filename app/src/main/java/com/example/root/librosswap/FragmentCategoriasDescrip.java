package com.example.root.librosswap;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCategoriasDescrip extends BaseVolleyFragment {

    Bundle datatransfer;
    String IDlibro;
    Dialog dialog;

    //Declaracion de string que guardaran datos del libeo seleccionado
    String idlibron;
    String codusern;
    String isbnn;
    String titulon;
    String autorn;
    String edicionn;
    String anion;
    String editorialn;
    String portadan;
    String descripn;
    String estadon;
    String ubicacionn;
    String categorian;
    String disponn;

    TextView isbn;
    TextView titulo;
    TextView autor;
    TextView edicion;
    TextView editorial;
    TextView descri;
    TextView ubicacion;
    TextView dispon;
    ImageView portada;

    TextView coduser;
    TextView nomuser;
    TextView ubiuser;

    Button solicita;
    Button cancela;
    Dialog dialoghappy;
    private static final long SPLASH_SCREEN_DELAY = 2000;
    public FragmentCategoriasDescrip() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_categorias_descrip, container, false);
        datatransfer=this.getArguments();
        IDlibro=datatransfer.getString("LibroId");
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.show();
        //Recibiendo datos del libro seleccionado
        ReceiveWSNoticiasDescrip();

        isbn=v.findViewById(R.id.libdes_isbn);
        titulo=v.findViewById(R.id.libdes_titulo);
        autor=v.findViewById(R.id.libdes_autor);
        edicion=v.findViewById(R.id.libdes_edicion);
        editorial=v.findViewById(R.id.libdes_editorial);
        descri=v.findViewById(R.id.libdes_descr);
        ubicacion=v.findViewById(R.id.libdes_ubic);
        dispon=v.findViewById(R.id.libdes_dispon);
        portada=v.findViewById(R.id.libdes_imgportada);

        coduser=v.findViewById(R.id.libdes_coduser);
        nomuser=v.findViewById(R.id.libdes_nombreuser);
        ubiuser=v.findViewById(R.id.libdes_ubicuser);

        solicita=v.findViewById(R.id.libdes_but_solicitar);
        solicita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dialoghappy = new Dialog(getContext());
                dialoghappy.setContentView(R.layout.dialog_confirmalibro);
                dialoghappy.show();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {

                        // Start the next activity

                        dialoghappy.dismiss();
                    }
                };

                // Simulate a long loading process on application startup.
                Timer timer = new Timer();
                timer.schedule(task, SPLASH_SCREEN_DELAY);

            }
        });
        cancela=v.findViewById(R.id.libdes_but_cancelar);
        cancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return v;
    }

    public void ReceiveWSNoticiasDescrip()
    {
        final String JsonURL = Constantes.GetLibrosID+IDlibro;
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET,JsonURL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonlibro = response.getJSONObject("Libros");
                            for (int i = 0; i < jsonlibro.length(); i++) {

                                idlibron=jsonlibro.getString("li_idLibros");
                                codusern=jsonlibro.getString("li_codUsuario");
                                isbnn=jsonlibro.getString("li_idISBN");
                                titulon=jsonlibro.getString("li_titulo");
                                autorn=jsonlibro.getString("li_autor");
                                edicionn=jsonlibro.getString("li_edicion");
                                anion=jsonlibro.getString("li_añoPublicacion");
                                editorialn=jsonlibro.getString("li_editorial");
                                portadan=jsonlibro.getString("li_portada");
                                descripn=jsonlibro.getString("li_descripcion");
                                estadon=jsonlibro.getString("li_estadoLibro")+"/10";
                                ubicacionn=jsonlibro.getString("li_ubicacion");
                                categorian=jsonlibro.getString("li_categoriaLibro");
                                disponn=jsonlibro.getString("li_disponibilidad");
                            }
                            Toast.makeText(getContext(), titulon, Toast.LENGTH_SHORT).show();
                            isbn.setText(isbnn);
                            titulo.setText(titulon);
                            autor.setText(autorn);
                            edicion.setText(edicionn);
                            editorial.setText(editorialn);
                            descri.setText(descripn);
                            ubicacion.setText(ubicacionn);
                            if (disponn.equals("1"))
                            {
                                dispon.setText("Libro Disponible");
                                dispon.setBackgroundColor(Color.GREEN);
                                solicita.setVisibility(View.VISIBLE);
                                cancela.setVisibility(View.GONE);
                            }else {
                                solicita.setVisibility(View.GONE);
                                cancela.setVisibility(View.VISIBLE);
                                if (disponn.equals("0"))
                                {
                                    dispon.setText("Libro en Transacción");
                                    dispon.setBackgroundColor(Color.MAGENTA);
                                }
                                else {
                                    if (disponn.equals("-1")){
                                        dispon.setText("Libro No Disponible");
                                        dispon.setBackgroundColor(Color.RED);
                                    } }
                            }
                            Glide.with(getContext()).load(portadan).into(portada);
                            ReceiveWSCodigoUser(codusern);
                        }
                        catch (JSONException e) {
                            dialog.dismiss();
                            onConnectionFailed("Problemas con la conexión");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        onConnectionFailed(error.toString());
                    }
                }
        );
        addToQueue(obreq);
    }

    public void ReceiveWSCodigoUser(String codigo)
    {
        final String JsonURL = Constantes.GetUsuarioID+"?codUsuario"+codigo;
        final JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET,JsonURL,null,
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonarray = response.getJSONArray("usuario");
                            UsersClass usuarios;
                            //listusuarios.clear();
                            for (int i = 0; i <= jsonarray.length(); i++) {
                                JSONObject jsonObject = jsonarray.getJSONObject(i);
                                String iduu=jsonObject.getString("us_idUsuario");
                                String codu=jsonObject.getString("us_codUsuario");
                                String nombreu=jsonObject.getString("us_nombres");
                                String apellidosu=jsonObject.getString("us_apellidos");
                                String direccu=jsonObject.getString("us_direccion");

                                coduser.setText(codu);
                                nomuser.setText(nombreu+' '+apellidosu);
                                ubiuser.setText(direccu);
                            }
                            dialog.dismiss();
                        }
                        catch (JSONException e) {
                            dialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        onConnectionFailed(error.toString());
                    }
                }
        );
        addToQueue(obreq);
    }

}
