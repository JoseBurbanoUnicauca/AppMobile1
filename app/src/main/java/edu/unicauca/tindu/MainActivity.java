package edu.unicauca.tindu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.unicauca.tindu.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irIniciar(View view){
        Intent i = new Intent(this, IniciarSesionActivity.class);
        startActivity(i);
    }

    public void irRegistrarse(View view){
        Intent i = new Intent(this, RegistrarseActivity.class);
        startActivity(i);
    }
}