package com.example.root.librosswap;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends BaseVolleyFragment {
    //Clase que guarda el usuario logueado
    SharedPrefUsuarios sesionuser;
    HashMap<String, String> user;

    Dialog dialogsubida;
    AlertDialog alertDialogBuilder;
    AlertDialog getAlertDialogBuilderlibro;
    private RadioButton radiotit;
    private RadioButton radioaut;
    private String searchstring;

    private ServerImageObject imageObject;
    private String encodedString;
    //Variable para enviar datos
    Bundle bundleidcat;
    private int posicionestado;
    private int posicioncategoria;

    //variables para accedera storage
    EditText portadalil;
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    String uripath;

    //String para insertar
    String titulols;
    String autordils;
    String lbsmls;
    String edicionls;
    String aniopublils;
    String editorialls;
    String descripls;
    String ubicacionls;
    String categorials;
    String estadolibrols;
    String[] arrayidcat;
    String[] arraynomcat;

    //variables de imagen
    String extension;
    private String filename;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Recibiendo variables
        NavigLibros Navigation= (NavigLibros) getActivity();
        Toolbar toolbar= (Toolbar) Navigation.findViewById(R.id.toolbar);
        toolbar.setTitle("Inicio");
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_home, container, false);

        sesionuser =new SharedPrefUsuarios(getContext());
        user = sesionuser.getUserDetails();
        ReceiveWSCategoriaLibros();
        //Casteo de  imagenes de categoria
        TextView cat1=v.findViewById(R.id.imagetext1);
        TextView cat2=v.findViewById(R.id.imagetext2);
        TextView cat3=v.findViewById(R.id.imagetext3);
        TextView cat4=v.findViewById(R.id.imagetext4);
        TextView cat5=v.findViewById(R.id.imagetext5);
        TextView cat6=v.findViewById(R.id.imagetext6);
        TextView cat7=v.findViewById(R.id.imagetext7);
        TextView cat8=v.findViewById(R.id.imagetext8);
        TextView cat9=v.findViewById(R.id.imagetext9);
        //procedimientos para eventos de clicks
        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(11);
            }
        });
        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(12);
            }
        });
        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(13);
            }
        });
        cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(14);
            }
        });
        cat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(15);
            }
        });
        cat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(16);
            }
        });
        cat7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(17);
            }
        });
        cat8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(18);
            }
        });
        cat9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(19);
            }
        });

        //Procedimiento button Agregar
        Button agregar=(Button) v.findViewById(R.id.Button1);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View Viewagregar = getLayoutInflater().inflate(R.layout.dialog_agregarlibros, null);
                AlertDialog.Builder Builder = new AlertDialog.Builder(getContext());
                final EditText titulol=Viewagregar.findViewById(R.id.diall_titulo);
                final EditText autordil=Viewagregar.findViewById(R.id.diall_autor);
                final EditText lbsml=Viewagregar.findViewById(R.id.diall_lsbm);
                final EditText edicionl=Viewagregar.findViewById(R.id.diall_edicion);
                final EditText aniopublil=Viewagregar.findViewById(R.id.diall_añopubl);
                portadalil=Viewagregar.findViewById(R.id.diall_imgtext);
                final EditText editoriall=Viewagregar.findViewById(R.id.diall_editorial);
                final EditText descripl=Viewagregar.findViewById(R.id.diall_descripcion);
                final EditText ubicacionl=Viewagregar.findViewById(R.id.diall_ubicacion);
                Button portadabutlil=Viewagregar.findViewById(R.id.diall_imgbut);

                Button btnagregar = (Button) Viewagregar.findViewById(R.id.btn_dialogbusb);
                Button btnsalir = (Button) Viewagregar.findViewById(R.id.btn_dialogbuss);

                Spinner spinnercategoria = Viewagregar.findViewById(R.id.spinnercategorialibro);
                ArrayAdapter<String> adaptadorcat = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arraynomcat);
                adaptadorcat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnercategoria.setAdapter(adaptadorcat);
                posicioncategoria= spinnercategoria.getSelectedItemPosition();

                final Spinner spinnerestado = Viewagregar.findViewById(R.id.spinnerestadolibro);
                String[] arraytipo = {"1/10","2/10","3/10","4/10","5/10","6/10","7/10","8/10","9/10","10/10"};
                ArrayAdapter<String> adaptadores = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arraytipo);
                adaptadores.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerestado.setAdapter(adaptadores);

                portadabutlil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showFileChooser();
                    }
                });
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

                        categorials=spinnerestado.getSelectedItem().toString();

                        estadolibrols=Integer.toString(posicionestado+1);

                        titulol.setError(null);
                        autordil.setError(null);
                        lbsml.setError(null);
                        edicionl.setError(null);
                        aniopublil.setError(null);
                        editoriall.setError(null);
                        descripl.setError(null);
                        ubicacionl.setError(null);

                        View focusView = null;

                        if (!Boolean.valueOf(new InternetConnection(getActivity().getApplicationContext()).isConnectingInternet()).booleanValue())
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
                                if (!TextUtils.isEmpty(lbsmls))
                                {   if (lbsmls.length()<11||lbsmls.length()>13)
                                    {
                                        lbsml.setError("El ISBN necesita tener 11 o 13 digitos");
                                        focusView=lbsml;
                                        focusView.requestFocus();
                                     }
                                     else {
                                    uploadSelectedImageToServer();
                                    }
                                }
                                else {
                                    uploadSelectedImageToServer();
                                }
                            }
                        }
                    }
                });
                btnsalir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getAlertDialogBuilderlibro.dismiss();
                    }
                });
                Builder.setView(Viewagregar);
                getAlertDialogBuilderlibro=Builder.create();
                getAlertDialogBuilderlibro.show();

            }
        });
        //Procedimiento button Listar
        Button listar= v.findViewById(R.id.Button2);
        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(0);
            }
        });
        //Procedimiento button buscar
        Button buscar= v.findViewById(R.id.Button3);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View promptView = getLayoutInflater().inflate(R.layout.dialog_buscarlibro, null);
                AlertDialog.Builder Builder = new AlertDialog.Builder(getContext());
                final EditText titulodia=promptView.findViewById(R.id.etxt_dialogtit);
                final EditText autordia=promptView.findViewById(R.id.etxt_dialogaut);
                radiotit =  promptView.findViewById(R.id.radio_tit);
                radioaut =  promptView.findViewById(R.id.radio_aut);
                if (radiotit.isChecked()==true){
                    titulodia.setVisibility(View.VISIBLE);
                }
                radioaut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        radiotit.setChecked(false);
                        radioaut.setChecked(true);
                        titulodia.setVisibility(View.INVISIBLE);
                        autordia.setVisibility(View.VISIBLE);
                    }
                });
                radiotit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        radiotit.setChecked(true);
                        radioaut.setChecked(false);
                        titulodia.setVisibility(View.VISIBLE);
                        autordia.setVisibility(View.INVISIBLE);
                    }
                });
                Button btnsearch =  promptView.findViewById(R.id.btn_dialogbusb);
                Button btnsalir =  promptView.findViewById(R.id.btn_dialogbuss);
                btnsearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (titulodia.getVisibility()==View.VISIBLE){
                            searchstring=titulodia.getText().toString();
                            setFragment(1);
                            alertDialogBuilder.dismiss();
                        }
                        else {
                            searchstring=autordia.getText().toString();
                            setFragment(2);
                            alertDialogBuilder.dismiss();
                        }

                    }
                });
                btnsalir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialogBuilder.dismiss();
                    }
                });
                Builder.setView(promptView);
                alertDialogBuilder=Builder.create();
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.show();
            }
        });
        return v;
    }

    public void setFragment(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentCategorias Cat = new FragmentCategorias();
        switch (position) {
            case 0:
                //Fragmento para listar todos los libros
                bundleidcat=new Bundle();
                bundleidcat.putString("ID","blistar");
                bundleidcat.putString("Search","sin categorias");
                Cat.setArguments(bundleidcat);
                fragmentTransaction.replace(R.id.fragmenti, Cat);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case 1:
                //Fragmento para listar por titulo
                bundleidcat=new Bundle();
                bundleidcat.putString("ID","bbuscartit");
                bundleidcat.putString("Search",searchstring);
                Cat.setArguments(bundleidcat);
                fragmentTransaction.replace(R.id.fragmenti, Cat);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case 2:
                //Fragmento para listar por autor
                bundleidcat=new Bundle();
                bundleidcat.putString("ID","bbuscaraut");
                bundleidcat.putString("Search",searchstring);
                Cat.setArguments(bundleidcat);
                fragmentTransaction.replace(R.id.fragmenti, Cat);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case 11:
                //Fragmento para listar por categoria 1
                bundleidcat=new Bundle();
                bundleidcat.putString("ID","catn1");
                bundleidcat.putString("Search","1");
                Cat.setArguments(bundleidcat);
                fragmentTransaction.replace(R.id.fragmenti, Cat);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case 12:
                //Fragmento para listar por categoria 2
                bundleidcat=new Bundle();
                bundleidcat.putString("ID","catn2");
                bundleidcat.putString("Search","2");
                Cat.setArguments(bundleidcat);
                fragmentTransaction.replace(R.id.fragmenti, Cat);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case 13:
                //Fragmento para listar por categoria 1
                bundleidcat=new Bundle();
                bundleidcat.putString("ID","catn3");
                bundleidcat.putString("Search","3");
                Cat.setArguments(bundleidcat);
                fragmentTransaction.replace(R.id.fragmenti, Cat);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case 14:
                //Fragmento para listar por categoria 1
                bundleidcat=new Bundle();
                bundleidcat.putString("ID","catn4");
                bundleidcat.putString("Search","4");
                Cat.setArguments(bundleidcat);
                fragmentTransaction.replace(R.id.fragmenti, Cat);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case 15:
                //Fragmento para listar por categoria 1
                bundleidcat=new Bundle();
                bundleidcat.putString("ID","catn5");
                bundleidcat.putString("Search","5");
                Cat.setArguments(bundleidcat);
                fragmentTransaction.replace(R.id.fragmenti, Cat);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case 16:
                //Fragmento para listar por categoria 1
                bundleidcat=new Bundle();
                bundleidcat.putString("ID","catn6");
                bundleidcat.putString("Search","6");
                Cat.setArguments(bundleidcat);
                fragmentTransaction.replace(R.id.fragmenti, Cat);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case 17:
                //Fragmento para listar por categoria 1
                bundleidcat=new Bundle();
                bundleidcat.putString("ID","catn7");
                bundleidcat.putString("Search","7");
                Cat.setArguments(bundleidcat);
                fragmentTransaction.replace(R.id.fragmenti, Cat);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case 18:
                //Fragmento para listar por categoria 1
                bundleidcat=new Bundle();
                bundleidcat.putString("ID","catn8");
                bundleidcat.putString("Search","8");
                Cat.setArguments(bundleidcat);
                fragmentTransaction.replace(R.id.fragmenti, Cat);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case 19:
                //Fragmento para listar por categoria 1
                bundleidcat=new Bundle();
                bundleidcat.putString("ID","catn9");
                bundleidcat.putString("Search","9");
                Cat.setArguments(bundleidcat);
                fragmentTransaction.replace(R.id.fragmenti, Cat);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

        }

    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccione una imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri selectimage = data.getData();

            uripath = getActivity().getContentResolver().getType(selectimage);

            if (uripath.equals("image/jpeg")||uripath.equals("image/webp")||uripath.equals("image/jpg")||uripath.equals("image/png"))
            {
                try {

                    filename=getFileName(selectimage);
                    String extension = filename.substring(filename.lastIndexOf(".") + 1);
                    portadalil.setText(filename);
                    //Asignar la imagen del filepath a un bitmap para luego insertarlo en un imageview
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectimage);
                    encodedString=getStringImage(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }}
            else {
                Toast.makeText(getContext(),"Por favor elija una imagen jpg/jpeg/png/webp", Toast.LENGTH_LONG).show();
            }

        }
    }

    public void ReceiveWSCategoriaLibros()
    {
        final String JsonURL = Constantes.Get_Insert_Categorias;
        final JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET,JsonURL,null,
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonarray = response.getJSONArray("categorias");
                            arrayidcat=new String[jsonarray.length()];
                            arraynomcat=new String[jsonarray.length()];
                            for (int i = 0; i <= jsonarray.length()+1; i++) {
                                JSONObject jsonObject = jsonarray.getJSONObject(i);
                                arrayidcat[i]=jsonObject.getString("cat_idCategoria");
                                arraynomcat[i]=jsonObject.getString("cat_nombreCategoria");
                            }
                            //Toast.makeText(getContext(), arraycategoria[2][1].toString()+arraycategoria[2][2].toString(), Toast.LENGTH_SHORT).show();
                        }
                        catch (JSONException e) {
                            //Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //onConnectionFailed(error.toString());
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
        );
        addToQueue(obreq);
    }
    public void uploadSelectedImageToServer()
    {
        dialogsubida = new Dialog(getContext());
        dialogsubida.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogsubida.setContentView(R.layout.dialog_loading);
        dialogsubida.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.InsertLibros,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        dialogsubida.dismiss();
                        Toast.makeText(getContext(), "Agregado Correctamente" , Toast.LENGTH_LONG).show();
                        getAlertDialogBuilderlibro.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        dialogsubida.dismiss();
                        Toast.makeText(getContext(), volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new Hashtable<String, String>();
                if(bitmap != null){
                    String image = getStringImage(bitmap);
                    params.put("image_data", image);
                }
                else {
                    filename="librodesconocido.jpeg";
                }
                params.put("image_tag",filename);
                params.put("codUsuario",user.get(SharedPrefUsuarios.KEY_CODUSUER).toString());
                params.put("idISBN",lbsmls);
                params.put("titulo",titulols);
                params.put("autor",autordils);
                params.put("edicion",edicionls);
                params.put("anoPublicacion",aniopublils);
                params.put("editorial",editorialls);
                params.put("descripcion",descripls);
                params.put("estadoLibro",estadolibrols);
                params.put("ubicacion",ubicacionls);
                params.put("categoria",categorials);
                params.put("disponibilidad",categorials);

                return params;
            }
        };
        addToQueue(stringRequest);
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    //clase de ayuda para recuperar nombre de imagen pickeada
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}
