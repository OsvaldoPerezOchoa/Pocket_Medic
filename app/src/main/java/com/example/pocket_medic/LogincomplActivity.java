package com.example.pocket_medic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class LogincomplActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logincompl);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.abrir_nav, R.string.cerrar_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new InicioFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_inicio);
        }
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.nav_inicio:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InicioFragment()).commit();
                break;

            case R.id.nav_citas:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CitasFragment()).commit();
                break;

            case R.id.nav_telefonos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TelefonosFragment()).commit();
                break;

            case R.id.nav_guias:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new GuiasFragment()).commit();
                break;

            case R.id.nav_recetas:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RecetasFragment()).commit();
                break;

            case R.id.nav_usuario:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new UsuarioFragment()).commit();
                break;

            case R.id.nav_direcciones:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DireccionesFragment()).commit();
                break;

            case R.id.nav_login:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LoginFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}