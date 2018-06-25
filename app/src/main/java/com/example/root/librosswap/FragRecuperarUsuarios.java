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
public class FragRecuperarUsuarios extends BaseVolleyFragment {
    //asignaciones de edittext
    private EditText etx_dniUsuario;
    private EditText etx_correo;
    private EditText etx_contraseña;
    private EditText etx_contraseñacc;
    //String para manipular datos de los EditText
    String s_dniUsuario;
    String s_correo;
    String s_contraseña;
    String s_contraseñacc;
    //Cuadro de Dialogo de Carga
    Dialog dialog;
    Dialog dialoghappy;
    private static final long SPLASH_SCREEN_DELAY = 2000;
    public FragRecuperarUsuarios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_frag_recuperar_usuarios, container, false);
        //casteo de vistas
        etx_dniUsuario=v.findViewById(R.id.regc_dni);
        etx_correo=v.findViewById(R.id.regc_correo);
        etx_contraseña=v.findViewById(R.id.regc_contraseñac);
        etx_contraseñacc=v.findViewById(R.id.regc_contraseñacc);

        Button bur=v.findViewById(R.id.regc_button);
        bur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_dniUsuario=etx_dniUsuario.getText().toString();
                s_correo=etx_correo.getText().toString();
                s_contraseña=etx_contraseña.getText().toString();
                s_contraseñacc=etx_contraseñacc.getText().toString();

                etx_dniUsuario.setError(null);
                etx_correo.setError(null);
                etx_contraseña.setError(null);
                etx_contraseñacc.setError(null);

                View focusView = null;

                if (!Boolean.valueOf(new InternetConnection(getActivity().getApplicationContext()).isConnectingInternet()).booleanValue())
                {
                    Snackbar.make(v, "Sin Conexion a Internet", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();;
                }
                else{
                    if (TextUtils.isEmpty(s_dniUsuario)
                            ||TextUtils.isEmpty(s_correo)
                            ||TextUtils.isEmpty(s_contraseña))
                    {
                        //dialog.dismiss();
                        Snackbar.make(v, "Necesita llenar los campos", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }
                    else {
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
                                    if (s_contraseña.equals(s_contraseñacc))
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
                        else {
                            etx_correo.setError("Escriba un email valido");
                            focusView=etx_correo;
                            focusView.requestFocus();
                        }
                    }
                }
            }
        });
        return  v;
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
                                JSONObject jsonusuario = response.getJSONObject("usuarios");

                                String oid = jsonusuario.getString("us_idUsuario");
                                String odni = jsonusuario.getString("us_dniUsuario");
                                String ocodu = jsonusuario.getString("us_codUsuario");
                                String otipu = jsonusuario.getString("us_tipoUsuario");
                                String onom = jsonusuario.getString("us_nombres");
                                String oapell = jsonusuario.getString("us_apellidos");
                                String ocorr = jsonusuario.getString("us_correo");
                                String osky = jsonusuario.getString("us_skype");
                                String odirr = jsonusuario.getString("us_direccion");
                                String ocontr = jsonusuario.getString("us_contraseña");
                                String oedad = jsonusuario.getString("us_edad");
                                String otel = jsonusuario.getString("us_telefono");
                                String ocel = jsonusuario.getString("us_celular");
                                String ogen = jsonusuario.getString("us_genero");
                                String oint = jsonusuario.getString("us_intereses");
                                String[] datosinsert=new String[]{
                                        oid,
                                        odni,
                                        ocodu,
                                        otipu,
                                        onom,
                                        oapell,
                                        ocorr,
                                        osky,
                                        odirr,
                                        s_contraseña,
                                        oedad,
                                        otel,
                                        ocel,
                                        ogen,
                                        oint};
                                UpdateRegister(datosinsert);
                            }
                            else
                                {
                                    Snackbar.make(getView(), "Este Email no existe", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
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

    public void UpdateRegister(String[] pas_array)
    {
        final String[] array_datos = pas_array;
        final String JsonURL2 = Constantes.updatetUsuario;
        HashMap<String, String> map = new HashMap<>();// Mapeo previo
        map.put("idUsuario",array_datos[0]);
        map.put("dniUsuario",array_datos[1]);
        map.put("codUsuario",array_datos[2]);
        map.put("tipoUsuario",array_datos[3]);
        map.put("nombres",array_datos[4]);
        map.put("apellidos",array_datos[5]);
        map.put("correo",array_datos[6]);
        map.put("skype",array_datos[7]);
        map.put("direccion",array_datos[8]);
        map.put("contrasena",array_datos[9]);
        map.put("edad",array_datos[10]);
        map.put("telefono",array_datos[11]);
        map.put("celular",array_datos[12]);
        map.put("genero",array_datos[13]);
        map.put("intereses",array_datos[14]);
        // Crear nuevo objeto Json basado en el mapa
        JSONObject jobject = new JSONObject(map);

        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.POST,JsonURL2,jobject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.dismiss();
                        dialoghappy = new Dialog(getContext());
                        dialoghappy.setContentView(R.layout.dialog_actualiza);
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
