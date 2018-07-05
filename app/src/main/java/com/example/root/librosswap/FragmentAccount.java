package com.example.root.librosswap;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAccount extends BaseVolleyFragment {

    SharedPrefUsuarios sesionuser;
    HashMap<String, String> user;

    //Casteo de Edittext
    TextView enombreapell;
    TextView ecoduser;
    TextView ecorreo;
    TextView eskype;
    TextView eestado;
    TextView etelefono;
    TextView ecelular;
    TextView egenero;
    TextView einteres;
    //String que contienen los datos guardados en el Shared preferences
    String snombreapell;
    String scoduser;
    String scorreo;
    String sskype;
    String sestado;
    String stelefono;
    String scelular;
    String sgenero;
    String sinteres;

    Button btactualiza;

    public FragmentAccount() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_account, container, false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Mi Cuenta");
        sesionuser =new SharedPrefUsuarios(getContext());
        user = sesionuser.getUserDetails();

        enombreapell=v.findViewById(R.id.txt_account_nombre);
        ecorreo=v.findViewById(R.id.txt_account_correo);
        ecoduser=v.findViewById(R.id.txt_account_coduser);
        eskype=v.findViewById(R.id.txt_account_skype);
        eestado=v.findViewById(R.id.txt_account_estado);
        etelefono=v.findViewById(R.id.txt_account_telefono);
        ecelular=v.findViewById(R.id.txt_account_celular);
        egenero=v.findViewById(R.id.txt_account_genero);
        einteres=v.findViewById(R.id.txt_account_intereses);
        btactualiza=v.findViewById(R.id.but_account_actualizar);

        snombreapell=user.get(SharedPrefUsuarios.KEY_NOM).toString()+" "+user.get(SharedPrefUsuarios.KEY_APELLIDO).toString();
        scoduser = user.get(SharedPrefUsuarios.KEY_CODUSUER).toString();
        scorreo = user.get(SharedPrefUsuarios.KEY_EMAIL).toString();
        sskype = user.get(SharedPrefUsuarios.KEY_SKYPE).toString();
        sestado = user.get(SharedPrefUsuarios.KEY_ESTADO).toString();

        switch (sestado){
            case "1":
                eestado.setText("Activo");
                break;
            case "0":
                eestado.setText("Suspendido");
                break;
            case "-1":
                eestado.setText("Eliminado");
                break;
        }
        stelefono = user.get(SharedPrefUsuarios.KEY_TEL).toString();
        scelular = user.get(SharedPrefUsuarios.KEY_CEL).toString();
        sgenero = user.get(SharedPrefUsuarios.KEY_GEN).toString();
        if (sgenero.equals("M")){
            egenero.setText("Masculino");
        }else {
            egenero.setText("Femenino");
        }
        sinteres = user.get(SharedPrefUsuarios.KEY_INTERES).toString();

        enombreapell.setText(snombreapell);
        ecoduser.setText(scoduser);
        ecorreo.setText(scorreo);
        eskype.setText(sskype);
        etelefono.setText(stelefono);
        ecelular.setText(scelular);;
        einteres.setText(sinteres);

        return v;
    }
}
