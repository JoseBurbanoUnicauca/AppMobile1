package edu.unicauca.tindu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.unicauca.tindu.R;
import com.google.firebase.auth.FirebaseAuth;

public class navegacion extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion);

        mAuth = FirebaseAuth.getInstance();
    }

    public void CerrarSesion (View view){
        mAuth.signOut();
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();

    }



}