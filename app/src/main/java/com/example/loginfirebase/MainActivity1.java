package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity1 extends AppCompatActivity {
    TextInputEditText editTextmail,editTextPassword;
    Button btnLogIn;
    FirebaseAuth mAuth1;
    ProgressBar prg;
    TextView txtView;
    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentuser = mAuth1.getCurrentUser();
        if(currentuser!=null){
            Intent intent = new Intent(getApplicationContext(),MainActivity1.class);
            startActivity(intent);
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        editTextmail = findViewById(R.id.email1);
        editTextPassword = findViewById(R.id.password1);
        prg = findViewById(R.id.progress1);
        txtView = findViewById(R.id.logInNow1);
        mAuth1 = FirebaseAuth.getInstance();
        btnLogIn = findViewById(R.id.btnLogin);

        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prg.setVisibility(View.VISIBLE);
                String email,password;
                email = String.valueOf(editTextmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(MainActivity1.this,"Enter email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity1.this,"Enter email",Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth1.signInWithEmailAndPassword(email, password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                prg.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(MainActivity1.this, "LogIn Sucessful .",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(MainActivity1.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    
                                }
                            }
                        });
            }
        });

    }
}