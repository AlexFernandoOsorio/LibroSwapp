package com.example.root.librosswap;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListarCat extends BaseVolleyFragment {

    public List<CategoClass> listcategorias = new ArrayList<>();
    RecyclerView recyclerView;
    AdapterCategorias catadapter;
    Dialog dialog;
    Dialog dialogsubida;

    AlertDialog Builder;

    public FragmentListarCat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_listar_cat, container, false);
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.show();
        ReceiveWSCat();

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerlist_categorias);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        catadapter = new AdapterCategorias(listcategorias);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(catadapter);
        catadapter.notifyDataSetChanged();
        recyclerView.setNestedScrollingEnabled(false);
        FloatingActionButton buttonfloat=v.findViewById(R.id.fab_addcategoria);
        buttonfloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View promptView = getLayoutInflater().inflate(R.layout.dialog_agregacategoria, null);
                Builder = new AlertDialog.Builder(getContext()).create();
                final EditText categoria=promptView.findViewById(R.id.diall_addcategoria);
                String cat =categoria.getText().toString();
                InsertWSCat(cat);
                Builder.setView(promptView);
                Builder.show();

            }
        });
        return  v;
    }

    public void ReceiveWSCat()
    {
        final String JsonURL = Constantes.GetCatall;
        final JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET,JsonURL,null,
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonarray = response.getJSONArray("categorias");
                            CategoClass libro;
                            listcategorias.clear();
                            for (int i = 0; i <= jsonarray.length(); i++) {
                                JSONObject jsonObject = jsonarray.getJSONObject(i);
                                String idcat=jsonObject.getString("cat_idCategoria");
                                String nombrecat=jsonObject.getString("cat_nombreCategoria");
                                libro=new CategoClass(idcat,nombrecat);
                                listcategorias.add(libro);
                                catadapter.notifyDataSetChanged();
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

    public void InsertWSCat(String categoria)
    {
        final String cat=categoria;
        dialogsubida = new Dialog(getContext());
        dialogsubida.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogsubida.setContentView(R.layout.dialog_loading);
        dialogsubida.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.InsertLibros,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        dialogsubida.dismiss();
                        Builder.dismiss();
                        Toast.makeText(getContext(), "Agregado Correctamente" , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        dialogsubida.dismiss();
                        Builder.dismiss();
                        Toast.makeText(getContext(), volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new Hashtable<String, String>();
                params.put("nombreCat",cat);
                return params;
            }
        };
        addToQueue(stringRequest);
    }

}
