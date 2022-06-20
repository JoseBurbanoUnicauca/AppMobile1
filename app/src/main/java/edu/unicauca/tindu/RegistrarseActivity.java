package edu.unicauca.tindu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.unicauca.tindu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrarseActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText correo;
    private EditText Password;
    private EditText contraseñaConfirmacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        mAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.correo);
        Password = findViewById(R.id.Password);
        contraseñaConfirmacion = findViewById(R.id.contraseñaConfirmacion);
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    public void registrarUsuario(View view){
        if (Password.getText().toString().equals(contraseñaConfirmacion.getText().toString())){

            mAuth.createUserWithEmailAndPassword(correo.getText().toString(), Password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(getApplication(),"Usuario creado - Ya puede iniciar sesión",Toast.LENGTH_LONG).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);


                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(getApplication(),"No fue posible crear el usuario.",Toast.LENGTH_LONG).show();
                                //updateUI(null);
                            }
                        }
                    });

        } else{
            Toast.makeText(getApplication(),"La contraseña no coincide",Toast.LENGTH_LONG).show();
        }


    }
}