package com.example.adam.manoauto.Login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adam.manoauto.MainActivity;
import com.example.adam.manoauto.R;
import com.example.adam.manoauto.Register.Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email, password;
    String sEmail,sPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.text_name_login);
        password = (EditText) findViewById(R.id.text_password_login);
        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=mAuth.getCurrentUser();
                if(user==null)
                {
                    return;
                }
                else{
                    Intent intent=new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void loginClick(View v) {
        sEmail = email.getText().toString().trim();
        sPassword = password.getText().toString().trim();
        if (!sEmail.isEmpty() && !sPassword.isEmpty()) {
            Toast.makeText(this,"Please wait",Toast.LENGTH_SHORT).show();
            login(sEmail, sPassword);
        }
        else {
            if(sEmail.isEmpty()) {
                Toast.makeText(this, "Please enter an email", Toast.LENGTH_LONG).show();
            }
            else if(sPassword.isEmpty()){
                Toast.makeText(this, "Please enter a password", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void registerClick(View v)
    {
        Intent intent=new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }

public void login(String email, String password)
{
    mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithEmail:failure", task.getException());
                        Toast.makeText(Login.this, "Please enter a valid email or password",
                                Toast.LENGTH_LONG).show();

                    }

                    // ...
                }
            });
}

public void facebookClick(View v)
{
    Toast.makeText(this,"Not implemented yet",Toast.LENGTH_SHORT).show();
}

}
