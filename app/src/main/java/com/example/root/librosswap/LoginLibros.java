package com.example.root.librosswap;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


/**
 * A login screen that offers login via email/password.
 */
public class LoginLibros extends BaseVolleyActivity implements LoaderCallbacks<Cursor> {

    //Declaracion del Shared Preferences para usuarios
    SharedPrefUsuarios sesionuser;

    //Declaracion de vistas para usuarios
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private TextView textCuenta;
    private TextView textRecuperaCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_libros);

        //Verificacion de SharedPreferences
        sesionuser =new SharedPrefUsuarios(getApplicationContext());

        Bundle extras=this.getIntent().getExtras();
        if (extras != null) {
            sesionuser.IntentLog(extras);
        }
        else {
            sesionuser.checkLogin();
        }
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginRequest();
            }
        });


        //ingreso a los fragmentos CRUD de usuario
        textCuenta=(TextView)findViewById(R.id.text_creacuenta);
        textRecuperaCuenta=(TextView)findViewById(R.id.text_recupcontra);
        textCuenta.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginLibros.this, UserManage.class);
                startActivity(intent);
            }
        });
        textRecuperaCuenta.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginLibros.this, UserManageUpdate.class);
                startActivity(intent);
            }
        });

    }

    //  Clases Implementadas
    //Clase para Identificacion mediante Web Services
    private void LoginRequest(){
        final String emailpri = mEmailView.getText().toString();
        final String pass = mPasswordView.getText().toString();
        final String mdpass= md5(pass);
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_conect);
        dialog.show();
        final String JsonURL = Constantes.GetUsuarioID+"?correoUsuario="+emailpri+"&&contraseñaUsuario="+pass;

        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET,JsonURL,null,
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject jsonuser = response.getJSONObject("usuarios");
                            for (int i = 0; i < jsonuser.length(); i++) {

                                String KEY_ID = jsonuser.getString("us_idUsuario");
                                String KEY_DNI = jsonuser.getString("us_dniUsuario");
                                String KEY_CODUSUER = jsonuser.getString("us_codUsuario");
                                String KEY_TIPO = jsonuser.getString("us_tipoUsuario");
                                String KEY_NOM = jsonuser.getString("us_nombres");
                                String KEY_APELLIDO = jsonuser.getString("us_apellidos");
                                String KEY_ESTADO = jsonuser.getString("us_estadoUsuario");
                                String KEY_EMAIL = jsonuser.getString("us_correo");
                                String KEY_SKYPE = jsonuser.getString("us_skype");
                                String KEY_DIRECCION = jsonuser.getString("us_direccion");
                                String KEY_PASS = jsonuser.getString("us_contraseña");
                                String KEY_EDAD = jsonuser.getString("us_edad");
                                String KEY_TEL = jsonuser.getString("us_telefono");
                                String KEY_CEL = jsonuser.getString("us_celular");
                                String KEY_GEN = jsonuser.getString("us_genero");
                                String KEY_INTERES = jsonuser.getString("us_intereses");

                                sesionuser.createLoginSession(KEY_ID,
                                                                KEY_DNI,
                                                                KEY_CODUSUER,
                                                                KEY_TIPO,
                                                                KEY_NOM,
                                                                KEY_APELLIDO,
                                                                KEY_ESTADO,
                                                                KEY_EMAIL,
                                                                KEY_SKYPE,
                                                                KEY_DIRECCION,
                                                                KEY_PASS,
                                                                KEY_EDAD,
                                                                KEY_CEL,
                                                                KEY_TEL,
                                                                KEY_GEN,
                                                                KEY_INTERES);
                            }
                            Intent intentnavigation =new Intent(LoginLibros.this,NavigLibros.class);
                            dialog.dismiss();
                            startActivity(intentnavigation);
                            finish();
                        }
                        catch (JSONException e) {
                            dialog.dismiss();
                            //Toast.makeText(getApplicationContext(),JsonURL, Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
                            //Toast.makeText(getApplicationContext(),"Email y/o Contraseña Incorrectos", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                        dialog.dismiss();
                        //Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),"Problema de conexión", Toast.LENGTH_LONG).show();

                    }
                }
        );
        addToQueue(obreq);

    }

    //Clase para encriptacion de Passwords
    public String md5(String s) {
        try {
            byte[] hash = MessageDigest.getInstance("MD5").digest(s.getBytes("UTF-8"));
            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                int i = b & MotionEventCompat.ACTION_MASK;
                if (i < 16) {
                    hex.append('0');
                }
                hex.append(Integer.toHexString(i));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e2);
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }
    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }
    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }
    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginLibros.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }
    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }
}

