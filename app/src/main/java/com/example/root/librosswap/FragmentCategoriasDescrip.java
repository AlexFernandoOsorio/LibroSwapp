package com.example.root.librosswap;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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

    TextView isbn;
    TextView titulo;
    TextView autor;
    TextView edicion;
    TextView editorial;
    TextView descri;
    TextView ubicacion;
    TextView dispon;
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
        dispon=v.findViewById(R.id.libdes_ubic);

        TextView coduser=v.findViewById(R.id.libdes_coduser);
        TextView nombreuser=v.findViewById(R.id.libdes_coduser);

        dispon.setText("Disponible");
        coduser.setText("falta verificar");
        coduser.setText("falta verificar");

        Button solicita=v.findViewById(R.id.libdes_but_solicitar);
        Button cancela=v.findViewById(R.id.libdes_but_cancelar);


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
                            }
                            dialog.dismiss();
                            Toast.makeText(getContext(), titulon, Toast.LENGTH_SHORT).show();
                            isbn.setText(isbnn);
                            titulo.setText(titulon);
                            autor.setText(autorn);
                            edicion.setText(edicionn);
                            editorial.setText(editorialn);
                            descri.setText(descripn);
                            ubicacion.setText(ubicacionn);
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

}
