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
public class FragmentListarLibros extends BaseVolleyFragment {

    public List<LibrosClass> listlibros = new ArrayList<>();
    RecyclerView recyclerView;
    AdapterListLibros libroadapter;
    Dialog dialog;

    public FragmentListarLibros() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_listar_libros, container, false);
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.show();
        ReceiveWSLibros();

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

    public void ReceiveWSLibros()
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
