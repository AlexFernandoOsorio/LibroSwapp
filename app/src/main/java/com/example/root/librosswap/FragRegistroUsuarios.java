package com.example.root.librosswap;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragRegistroUsuarios extends BaseVolleyFragment {
    //asignaciones de edittext
    private EditText etx_dniUsuario;
    private EditText etx_codUsuario;
    private EditText etx_nombres;
    private EditText etx_apellidos;
    private EditText etx_correo;
    private EditText etx_skype;
    private EditText etx_direccion;
    private EditText etx_contraseña;
    private EditText etx_contraseñac;
    private EditText etx_edad;
    private EditText etx_telefono;
    private EditText etx_celular;
    private EditText etx_intereses;
    //Integer para identificar el tipo de usuario,genero
    int intposiciontipo;
    int intposiciongenero;
    //String para manipular datos de los EditText
    String s_dniUsuario;
    String s_codUsuario;
    String s_nombres;
    String s_apellidos;
    String s_correo;
    String s_skype;
    String s_direccion;
    String s_contraseña;
    String s_contraseñac;
    String s_edad;
    String s_telefono;
    String s_celular;

    String s_genero;
    String s_tipo;

    String s_intereses;
    //Cuadro de Dialogo de Carga
    Dialog dialog;
    Dialog dialoghappy;
    private static final long SPLASH_SCREEN_DELAY = 2000;
    public FragRegistroUsuarios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_frag_registro_usuarios, container, false);

        //casteo de vistas
        etx_dniUsuario=v.findViewById(R.id.reg_dni);
        etx_codUsuario=v.findViewById(R.id.reg_codigo);
        etx_nombres=v.findViewById(R.id.reg_nombres);
        etx_apellidos=v.findViewById(R.id.reg_apellidos);
        etx_correo=v.findViewById(R.id.reg_correo);
        etx_skype=v.findViewById(R.id.reg_skype);
        etx_direccion=v.findViewById(R.id.reg_direccion);
        etx_contraseña=v.findViewById(R.id.reg_contraseña1);
        etx_contraseñac=v.findViewById(R.id.reg_contraseña2);
        etx_edad=v.findViewById(R.id.reg_edad);
        etx_telefono=v.findViewById(R.id.reg_telefono);
        etx_celular=v.findViewById(R.id.reg_celular);
        etx_intereses=v.findViewById(R.id.reg_intereses);

        Spinner spinnertipo = v.findViewById(R.id.spinnertipo);
        String[] arraytipo = {"Alumno","Docente","Administrativo"};
        ArrayAdapter<String> adaptadort = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arraytipo);
        adaptadort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertipo.setAdapter(adaptadort);
        intposiciontipo= spinnertipo.getSelectedItemPosition();

        Spinner spinnergenero = v.findViewById(R.id.spinnergenero);
        String[] arraygenero = {"Masculino","Femenino"};
        ArrayAdapter<String> adaptadorg = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arraygenero);
        adaptadorg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnergenero.setAdapter(adaptadorg);
        intposiciongenero= spinnertipo.getSelectedItemPosition();

        Button bur=v.findViewById(R.id.reg_button);
        bur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_dniUsuario=etx_dniUsuario.getText().toString();
                s_codUsuario=etx_codUsuario.getText().toString();
                s_nombres=etx_nombres.getText().toString();
                s_apellidos=etx_apellidos.getText().toString();
                s_correo=etx_correo.getText().toString();
                s_skype=etx_skype.getText().toString();
                s_direccion=etx_direccion.getText().toString();
                s_contraseña=etx_contraseña.getText().toString();
                s_contraseñac=etx_contraseñac.getText().toString();
                s_edad=etx_edad.getText().toString();
                s_telefono=etx_telefono.getText().toString();
                s_celular=etx_celular.getText().toString();
                s_intereses=etx_intereses.getText().toString();

                etx_dniUsuario.setError(null);
                etx_codUsuario.setError(null);
                etx_nombres.setError(null);
                etx_apellidos.setError(null);
                etx_correo.setError(null);
                etx_skype.setError(null);
                etx_direccion.setError(null);
                etx_contraseña.setError(null);
                etx_contraseñac.setError(null);
                etx_edad.setError(null);
                etx_telefono.setError(null);
                etx_celular.setError(null);
                etx_intereses.setError(null);

                View focusView = null;

                if (!Boolean.valueOf(new InternetConnection(getActivity().getApplicationContext()).isConnectingInternet()).booleanValue())
                {
                    Snackbar.make(v, "Sin Conexion a Internet", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();;
                }
                else{
                    if (TextUtils.isEmpty(s_dniUsuario)
                            ||TextUtils.isEmpty(s_codUsuario)
                            ||TextUtils.isEmpty(s_nombres)
                            ||TextUtils.isEmpty(s_apellidos)
                            ||TextUtils.isEmpty(s_correo)
                            ||TextUtils.isEmpty(s_direccion)
                            ||TextUtils.isEmpty(s_contraseña)
                            ||TextUtils.isEmpty(s_contraseñac)
                            ||TextUtils.isEmpty(s_edad)
                            ||TextUtils.isEmpty(s_celular)
                            ||TextUtils.isEmpty(s_intereses))
                    {
                        //dialog.dismiss();
                        Snackbar.make(v, "Necesita llenar los campos", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }
                    else {
                        if (TextUtils.isEmpty(s_telefono))
                        {
                            s_telefono="0000000000";
                        }
                        if (TextUtils.isEmpty(s_skype))
                        {
                            s_skype="Desconocido";
                        }
                        if (s_correo.contains("@"))
                        {   if (s_contraseña.length()<6)
                        {
                            etx_contraseña.setError("La contraseña necesita 6 o mas digitos");
                            focusView=etx_contraseña;
                            focusView.requestFocus();
                        }
                        else {
                            if (s_dniUsuario.length()<8)
                            {
                                etx_dniUsuario.setError("El DNI necesita 8 digitos");
                                focusView=etx_dniUsuario;
                                focusView.requestFocus();
                            }
                            else {
                                if (s_celular.length()<8){
                                    etx_celular.setError("El Nro de celular necesita 8 o mas digitos");
                                    focusView=etx_celular;
                                    focusView.requestFocus();
                                }
                                else {
                                    if (s_contraseña.equals(s_contraseñac))
                                    {
                                        ReceiverWSEmail(s_correo);
                                    }
                                    else
                                    {
                                        Snackbar.make(v, "Sus contraseñas son diferentes", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }
                                }
                            }
                        }
                        }
                        else {
                            etx_correo.setError("Escriba un email valido");
                            focusView=etx_correo;
                            focusView.requestFocus();
                        }
                    }
                }
            }
        });

        return v;
    }

    public void ReceiverWSEmail(String pas_email)
    {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_loading);
        dialog.show();
        final String ws_correo = pas_email;
        final String JsonURL = Constantes.GetUsuarioID+"?correoUsuario="+ws_correo;
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET,JsonURL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String jsonobjetestado = response.getString("estado");
                            if (jsonobjetestado.equals("1")){
                                Snackbar.make(getView(), "Este email ya existe,Intente con otro", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                            else {
                                switch (intposiciongenero) {
                                    case 0:
                                        s_genero="M";
                                        break;
                                    case 1:
                                        s_genero="F";
                                        break;
                                }
                                switch (intposiciontipo) {
                                    case 0:
                                        s_tipo="3";//alumno
                                        break;
                                    case 1:
                                        s_tipo="2";//docente
                                        if (TextUtils.isEmpty(s_codUsuario))
                                        {
                                            s_codUsuario="2222222222";
                                        }
                                        break;
                                    case 2:
                                        s_tipo="1";//administrativo
                                        if (TextUtils.isEmpty(s_codUsuario))
                                        {
                                            s_codUsuario="1111111111";
                                        }
                                        break;
                                }
                                String[] datosinsert=new String[]{
                                        s_dniUsuario,
                                        s_codUsuario,
                                        s_tipo,
                                        s_nombres,
                                        s_apellidos,
                                        s_correo,
                                        s_skype,
                                        s_direccion,
                                        s_contraseña,
                                        s_edad,
                                        s_telefono,
                                        s_celular,
                                        s_genero,
                                        s_intereses};
                                InsertRegister(datosinsert);
                                //Toast.makeText(getContext(), datosinsert[2],Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (JSONException e) {
                            dialog.dismiss();
                            //onConnectionFailed("Email Invalido");
                            Toast.makeText(getContext(), e.toString(),Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        //onConnectionFailed("Error con la conexión");
                        Toast.makeText(getContext(), error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
        );
        addToQueue(obreq);

    }

    public void InsertRegister(String[] pas_array)
    {
        final String[] array_datos = pas_array;
        final String JsonURL2 = Constantes.insertUsuario;
        HashMap<String, String> map = new HashMap<>();// Mapeo previo
        map.put("dniUsuario",array_datos[0]);
        map.put("codUsuario",array_datos[1]);
        map.put("tipoUsuario",array_datos[2]);
        map.put("nombres",array_datos[3]);
        map.put("apellidos",array_datos[4]);
        map.put("correo",array_datos[5]);
        map.put("skype",array_datos[6]);
        map.put("direccion",array_datos[7]);
        map.put("contrasena",array_datos[8]);
        map.put("edad",array_datos[9]);
        map.put("telefono",array_datos[10]);
        map.put("celular",array_datos[11]);
        map.put("genero",array_datos[12]);
        map.put("intereses",array_datos[13]);
        // Crear nuevo objeto Json basado en el mapa
        JSONObject jobject = new JSONObject(map);

        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.POST,JsonURL2,jobject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.dismiss();
                        dialoghappy = new Dialog(getContext());
                        dialoghappy.setContentView(R.layout.dialog_creacion);
                        dialoghappy.show();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {

                                // Start the next activity
                                Intent mainIntent = new Intent().setClass(
                                        getActivity(), LoginLibros.class);
                                startActivity(mainIntent);
                                getActivity().finish();
                                dialoghappy.dismiss();
                            }
                        };

                        // Simulate a long loading process on application startup.
                        Timer timer = new Timer();
                        timer.schedule(task, SPLASH_SCREEN_DELAY);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        //onConnectionFailed("Intentelo nuevamente");
                        Toast.makeText(getContext(), error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
        ){

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("Accept", "application/json");
                return headers;
            }
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8" + getParamsEncoding();
            }

        };
        addToQueue(obreq);
    }

}
