package com.example.root.librosswap;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCategoriasDescrip extends BaseVolleyFragment {

    Bundle datatransfer;
    String ID;
    Dialog dialog;
    public FragmentCategoriasDescrip() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_categorias_descrip, container, false);
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.show();
        //Recibiendo datos tranmitidois
        ID=datatransfer.getString("LibroId");



        return v;
    }

    public void ReceiveWSNoticiasDescrip()
    {
        final String JsonURL = Constantes.GetLibrosID+ID;
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET,JsonURL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonarray = response.getJSONArray("objeto");
                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject jsonObject = jsonarray.getJSONObject(i);
                                /*String titulond=jsonObject.getString("c_titulo");
                                String asuntond=jsonObject.getString("c_asunto");
                                String detallend=jsonObject.getString("c_detalle");
                                String fechand=jsonObject.getString("c_fechaRegistro");

                                titulo.setText(titulond);
                                asunto.setText(asuntond);
                                if(detallend != null)
                                {detalle.setText(Html.fromHtml(detallend));
                                }
                                else {detalle.setText("");}
                                fecha.setText(fechand);*/
                            }
                            dialog.dismiss();
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
