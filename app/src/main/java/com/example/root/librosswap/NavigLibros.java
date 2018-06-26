package com.example.root.librosswap;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

public class NavigLibros extends BaseVolleyActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Clase que guarda el usuario logueado
    SharedPrefUsuarios sesionuser;

    //variablepara identificar  tipo de usuario
    String tipousuario;
    //Variablepara enviar datos
    Bundle bundleidcat;
    //Declaracion de vistas para libros
    ImageView drawerProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navig_libros);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("Inicio");
        setSupportActionBar(toolbar);
        sesionuser =new SharedPrefUsuarios(getApplicationContext());
        HashMap<String, String> user = sesionuser.getUserDetails();

        switch (user.get(SharedPrefUsuarios.KEY_TIPO).toString()) {
            case "1":
                tipousuario="Alumno";//alumno
                break;
            case "2":
                tipousuario="Docente";//docente
                break;
            case "3":
                tipousuario="Administrador";//administrativo
                break;
                default:
                    tipousuario="Administrador";
                    break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        TextView nav_headertipo= headerView.findViewById(R.id.text_nav_header1);
        TextView nav_headernombre=headerView.findViewById(R.id.text_nav_header2);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.panda);

        RoundedBitmapDrawable rounded =   RoundedBitmapDrawableFactory.create(getResources(), bitmap);

        rounded.setCornerRadius(bitmap.getWidth());
        rounded.setCircular(true);

        drawerProfile = (ImageView) headerView.findViewById(R.id.imageView_nav_header);
        drawerProfile.setImageDrawable(rounded);

        nav_headertipo.setText("Bienvenido  "+ tipousuario);
        nav_headernombre.setText(user.get(SharedPrefUsuarios.KEY_NOM));
        //Procedimiento de Transaccion a un fragment
        setFragmentn(0);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navig_libros, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.menu_inicio) {
            setFragmentn(0);
        } else if (id == R.id.menu_cuenta) {

        } else if (id == R.id.menu_libros) {
            setFragmentn(2);
        } else if (id == R.id.menu_sesion) {
            sesionuser.logoutUser();
            finish();
        } else if (id == R.id.menu_listarli) {
            setFragmentn(2);
        } else if (id == R.id.menu_categoria) {
            setFragmentn(3);
        } else if (id == R.id.menu_listaruser) {
            setFragmentn(1);
        } else if (id == R.id.menu_actividad) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void setFragmentn(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Fragmentos a  Transaction
        FragmentHome Home = new FragmentHome();
        FragmentListarUsers Users=new FragmentListarUsers();
        FragmentListarLibros libros= new FragmentListarLibros();
        FragmentListarCat categorias= new FragmentListarCat();

        switch (position) {
            case 0:
                fragmentTransaction.replace(R.id.fragmenti, Home);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction.replace(R.id.fragmenti, Users);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentTransaction.replace(R.id.fragmenti, libros);
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentTransaction.replace(R.id.fragmenti, categorias);
                fragmentTransaction.commit();
                break;
        }
    }

}
