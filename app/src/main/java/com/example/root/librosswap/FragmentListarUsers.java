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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListarUsers extends BaseVolleyFragment {
    Dialog dialog;

    public List<UsersClass> listusuarios = new ArrayList<>();
    RecyclerView recyclerView;
    AdapterListUsuarios ussuariosadapter;

    public FragmentListarUsers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_listar_users, container, false);
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.show();
        ReceiveWSUsers();

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerlist_user);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        ussuariosadapter = new AdapterListUsuarios(listusuarios);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(ussuariosadapter);
        ussuariosadapter.notifyDataSetChanged();
        recyclerView.setNestedScrollingEnabled(false);


        return v;
    }

    public void ReceiveWSUsers()
    {
        final String JsonURL = Constantes.GetUsuarios;
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
                                String dniu=jsonObject.getString("us_dniUsuario");
                                String tipou=jsonObject.getString("us_tipoUsuario");
                                String nombreu=jsonObject.getString("us_nombres");
                                String apellidosu=jsonObject.getString("us_apellidos");
                                String estadousueru=jsonObject.getString("us_estadoUsuario");
                                String correou=jsonObject.getString("us_correo");
                                String celularu=jsonObject.getString("us_celular");
                                String generou=jsonObject.getString("us_genero");
                                switch (tipou) {
                                    case "1":
                                        tipou="Alumno";//alumno
                                        break;
                                    case "2":
                                        tipou="Docente";//docente
                                        break;
                                    case "3":
                                        tipou="Administrador";//administrativo
                                        break;
                                }
                                switch (generou) {
                                    case "M":
                                        generou="Masculino";//
                                        break;
                                    case "F":
                                        generou="Femenino";//
                                        break;
                                }
                                usuarios=new UsersClass("DNI "+dniu,tipou,nombreu,apellidosu,estadousueru,
                                        correou,"Cel "+celularu,generou);
                                listusuarios.add(usuarios);
                                ussuariosadapter.notifyDataSetChanged();
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
