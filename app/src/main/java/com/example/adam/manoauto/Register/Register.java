package com.example.adam.manoauto.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adam.manoauto.MainActivity;
import com.example.adam.manoauto.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email, password, repeatPass;
    String sEmail, sPassword, sRepeatPass;
    private boolean signInResult = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText) findViewById(R.id.text_name);
        password = (EditText) findViewById(R.id.text_password);
        repeatPass = (EditText) findViewById(R.id.text_password_repeat);
        mAuth = FirebaseAuth.getInstance();

    }

    public void registerClick(View v) {
        sEmail = email.getText().toString().trim();
        sPassword = password.getText().toString().trim();
        sRepeatPass = repeatPass.getText().toString().trim();
        if (!sEmail.isEmpty() && !sPassword.isEmpty() && !sRepeatPass.isEmpty()) {
            if (sPassword.equals(sRepeatPass)) {
                Toast.makeText(this,"Please wait",Toast.LENGTH_SHORT).show();
                createUser(email.getText().toString(), password.getText().toString());
                if (signInResult == true) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            } else {
                Toast.makeText(this, "Passwords need to be the same", Toast.LENGTH_LONG).show();
            }
        } else if (sEmail.isEmpty() || sPassword.isEmpty() || sRepeatPass.isEmpty()) {
            Toast.makeText(this, "Email or password must not be empty", Toast.LENGTH_LONG).show();
        }
    }

    public void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            signInResult = true;

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Please enter a valid email or or password!\n" +
                                            "Password needs to be minimum 6 characters long",
                                    Toast.LENGTH_SHORT).show();
                            signInResult = false;
                        }
                    }
                });
    }
}

