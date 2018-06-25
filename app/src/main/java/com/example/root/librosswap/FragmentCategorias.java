package com.example.root.librosswap;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCategorias extends BaseVolleyFragment {

    public List<LibrosClass> listlibros = new ArrayList<>();
    RecyclerView recyclerView;
    AdapterLibros libroadapter;
    Dialog dialog;

    //Variable para recibir datos
    Bundle bundlecat;
    String bundledat;
    String bundledatsearch;
    String tituloFrag;
    public FragmentCategorias() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_fragment_categorias, container, false);

        //Recuperacion de Bundle para identificar tipo de fragment
        bundlecat=this.getArguments();
        bundledat=bundlecat.getString("ID");
        bundledatsearch=bundlecat.getString("Search");
        // Inflar el layout con los datos del Bundle
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        Identificador();
        toolbar.setTitle(tituloFrag);
        //Inicio de la invocacion de metodos
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.show();
        //Metodo invocado para recibir los datos de los libros
        //Identificador();

        recyclerView = (RecyclerView) v.findViewById(R.id.notifications_list);
        libroadapter = new AdapterLibros(listlibros);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(libroadapter);

        libroadapter.notifyDataSetChanged();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            FragmentManager fragmentManager;
            FragmentTransaction fragmentTransaction;
            FragmentCategoriasDescrip FragCatDescr;
            Bundle bundlecat;
            @Override
            public void onClick(View view, int position) {
                LibrosClass itemSelec = listlibros.get(position);
                String notiid = itemSelec.getIdLibros();
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                FragCatDescr = new FragmentCategoriasDescrip();
                bundlecat =new Bundle();
                bundlecat.putString("LibroId",notiid);
                FragCatDescr.setArguments(bundlecat);
                fragmentTransaction.replace(R.id.fragmenti, FragCatDescr);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }));

        return v;
    }

    //Metodo para recibir todos los Libros del servicio rest
    public void ReceiveWSLibrosall()
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
                                String lsbmn=jsonObject.getString("li_idISBN");
                                String titulon=jsonObject.getString("li_titulo");
                                String autorn=jsonObject.getString("li_autor");
                                String estadon=jsonObject.getString("li_estadoLibro")+"/10";
                                String portadan=jsonObject.getString("li_portada");
                                libro=new LibrosClass(lsbmn,titulon,autorn,estadon,portadan);
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
    public void ReceiveWSLibrosTitulo()
    {
        final String JsonURL = Constantes.GetLibrosTitulo+"%25"+bundledatsearch+"%25";

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
                                String lsbmn=jsonObject.getString("li_idLSBM");
                                String titulon=jsonObject.getString("li_titulo");
                                String autorn=jsonObject.getString("li_autor");
                                String estadon=jsonObject.getString("li_estadoLibro")+"/10";
                                String portadan=jsonObject.getString("li_portada");
                                libro=new LibrosClass(lsbmn,titulon,autorn,estadon,portadan);
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
                        //Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        addToQueue(obreq);
    }
    public void ReceiveWSLibrosAutor()
    {
        final String JsonURL = Constantes.GetLibrosAutor+"%25"+bundledatsearch+"%25";
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
                                String lsbmn=jsonObject.getString("li_idLSBM");
                                String titulon=jsonObject.getString("li_titulo");
                                String autorn=jsonObject.getString("li_autor");
                                String estadon=jsonObject.getString("li_estadoLibro")+"/10";
                                String portadan=jsonObject.getString("li_portada");
                                libro=new LibrosClass(lsbmn,titulon,autorn,estadon,portadan);
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
    public void ReceiveWSLibrosCategoria()
    {
        final String JsonURL = Constantes.GetLibrosCat+bundledatsearch;
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
                                String lsbmn=jsonObject.getString("li_idLSBM");
                                String titulon=jsonObject.getString("li_titulo");
                                String autorn=jsonObject.getString("li_autor");
                                String estadon=jsonObject.getString("li_estadoLibro")+"/10";
                                String portadan=jsonObject.getString("li_portada");
                                libro=new LibrosClass(lsbmn,titulon,autorn,estadon,portadan);
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
                        //onConnectionFailed(error.toString());
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
        );
        addToQueue(obreq);
    }

    public  void Identificador()
    {
        switch (bundledat){
            case "blistar":
                tituloFrag="Libros";
                ReceiveWSLibrosall();
                break;
            case "bbuscartit":
                tituloFrag="Libros por Titulo";
                ReceiveWSLibrosTitulo();
                break;
            case "bbuscaraut":
                tituloFrag="Libros por Autor";
                ReceiveWSLibrosAutor();
                break;
            case "cat11":
                tituloFrag="Libros de "+getString(R.string.cat1);
                ReceiveWSLibrosCategoria();
                break;
            case "cat12":
                tituloFrag="Libros de "+getString(R.string.cat2);
                ReceiveWSLibrosCategoria();
                break;
            case "cat14":
                tituloFrag="Libros de "+getString(R.string.cat4);
                ReceiveWSLibrosCategoria();
                break;
            case "cat15":
                tituloFrag="Libros de "+getString(R.string.cat5);
                ReceiveWSLibrosCategoria();
                break;
            case "cat17":
                tituloFrag="Libros de "+getString(R.string.cat7);
                ReceiveWSLibrosCategoria();
                break;
            case "cat18":
                tituloFrag="Libros de "+getString(R.string.cat8);
                ReceiveWSLibrosCategoria();
                break;
            case "cat19":
                tituloFrag="Libros de "+getString(R.string.cat9);
                ReceiveWSLibrosCategoria();
                break;
            case "cat110":
                tituloFrag="Libros de "+getString(R.string.cat10);
                ReceiveWSLibrosCategoria();
                break;
            case "cat111":
                tituloFrag="Libros de "+getString(R.string.cat11);
                break;
        }

    }
    //Clase auxiliar del  Recyclerview
    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private FragmentCategorias.ClickListener clickListener;
        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final FragmentCategorias.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }
        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }
        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }
    public interface ClickListener {
        void onClick(View view, int position);
    }
}
