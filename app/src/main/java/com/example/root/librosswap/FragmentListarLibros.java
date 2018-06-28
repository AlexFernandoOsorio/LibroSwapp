package com.example.root.librosswap;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListarLibros extends BaseVolleyFragment {

    public List<LibrosClass> listlibros = new ArrayList<>();
    RecyclerView recyclerView;
    AdapterListLibros libroadapter;
    Dialog dialog;

    //Variables para recuperar datos del usuario
    SharedPrefUsuarios sesionuser;
    HashMap<String, String> user;

    public FragmentListarLibros() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_listar_libros, container, false);
        //Inflamos los parametros del usuario logueado
        sesionuser =new SharedPrefUsuarios(getContext());
        user = sesionuser.getUserDetails();

        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.show();
        Bundle IdentificadorLista=this.getArguments();
        Integer i=IdentificadorLista.getInt("id");
        //Toast.makeText(getContext(), Integer.toString(i) , Toast.LENGTH_LONG).show();
        if (i==0)
        {
            RecieveWSLibrosUser();//Listado de libros por codigo de usuario logueado
        }
        else
        {
            RecieveWSLibros();//Listado de todos los libros
        }

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerlist_libros);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        libroadapter = new AdapterListLibros(listlibros);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(libroadapter);
        libroadapter.notifyDataSetChanged();
        recyclerView.setNestedScrollingEnabled(false);


        return v;
    }

    public void RecieveWSLibros()
    {
        final String JsonURL = Constantes.GetLibrosall;
        final JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET,JsonURL,null,
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonarray = response.getJSONArray("libros");
                            LibrosClass libro;
                            listlibros.clear();
                            for (int i = 0; i <= jsonarray.length(); i++) {
                                JSONObject jsonObject = jsonarray.getJSONObject(i);
                                String idlibro=jsonObject.getString("li_idLibros");
                                String coduser=jsonObject.getString("li_codUsuario");
                                String isbnn=jsonObject.getString("li_idISBN");
                                String titulon=jsonObject.getString("li_titulo");
                                String autorn=jsonObject.getString("li_autor");
                                String edicionn=jsonObject.getString("li_edicion");
                                String anion=jsonObject.getString("li_añoPublicacion");
                                String editorialn=jsonObject.getString("li_editorial");
                                String portadan=jsonObject.getString("li_portada");
                                String descrip=jsonObject.getString("li_descripcion");
                                String estadon=jsonObject.getString("li_estadoLibro")+"/10";
                                String ubicacionn=jsonObject.getString("li_ubicacion");
                                String categorian=jsonObject.getString("li_categoriaLibro");

                                libro=new LibrosClass(idlibro,coduser,isbnn,titulon,autorn,edicionn,anion,editorialn,
                                        portadan,descrip,estadon,ubicacionn,categorian);
                                listlibros.add(libro);
                                libroadapter.notifyDataSetChanged();
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
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        onConnectionFailed(error.toString());
                    }
                }
        );
        addToQueue(obreq);
    }

    public void RecieveWSLibrosUser()
    {
        final String JsonURL = Constantes.GetLibrosUsuario+user.get(SharedPrefUsuarios.KEY_CODUSUER).toString();
        final JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET,JsonURL,null,
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonarray = response.getJSONArray("Libros");
                            LibrosClass libro;
                            listlibros.clear();
                            for (int i = 0; i <= jsonarray.length(); i++) {
                                JSONObject jsonObject = jsonarray.getJSONObject(i);
                                String coduser=jsonObject.getString("li_codUsuario");
                                String titulon=jsonObject.getString("li_titulo");
                                String autorn=jsonObject.getString("li_autor");
                                String estadon=jsonObject.getString("li_estadoLibro")+"/10";
                                String portadan=jsonObject.getString("li_portada");
                                libro=new LibrosClass(coduser,titulon,autorn,estadon,portadan);
                                listlibros.add(libro);
                                libroadapter.notifyDataSetChanged();
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
