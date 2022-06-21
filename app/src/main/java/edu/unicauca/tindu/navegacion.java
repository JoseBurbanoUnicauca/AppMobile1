package edu.unicauca.tindu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import edu.unicauca.tindu.R;
import com.google.firebase.auth.FirebaseAuth;

public class navegacion extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion);

        mAuth = FirebaseAuth.getInstance();

        //Definicion de la barra de menu
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar();
        ActionBar actionBar = getSupportActionBar();

        //Titulo de la barra de menu
        actionBar.setTitle("  TindU | Menu Principal");

    }
    //Inflate del menu en la barra de aplicacion
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Acciones del menu en la barra de aplicacion
    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {

        switch (item.getItemId()){
            case R.id.action_config:
                Intent i2 = new Intent(getApplicationContext(), Configuracion.class);
                startActivity(i2);
                break;
            case R.id.action_creditos:
                Intent i3 = new Intent(getApplicationContext(), Creditos.class);
                startActivity(i3);
                break;
            case R.id.action_cerrar:
                mAuth.signOut();
                Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i1);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

        //BOTONES DE PRUEBA
    /*public void CerrarSesion(View view) {
        mAuth.signOut();
        Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i1);
        finish();
    }

    public void Configuracion(View view) {
        Intent i2 = new Intent(getApplicationContext(), Configuracion.class);
        startActivity(i2);
        //finish();
    }
    public void Creditos(View view) {
        Intent i3 = new Intent(getApplicationContext(), Creditos.class);
        startActivity(i3);
        //finish();
    }*/
}