package com.example.loginfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    FirebaseUser fbUser;
    FirebaseAuth auth1;
    Button btnLogOut;
    TextView txtViewMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth1 = FirebaseAuth.getInstance();
        btnLogOut = findViewById(R.id.btnMain);
        txtViewMain = findViewById(R.id.txtViewMain);
        fbUser = auth1.getCurrentUser();
        if(fbUser==null){
            Intent intent = new Intent(getApplicationContext(),MainActivity1.class);
            startActivity(intent);
            finish();
        }
        else{
            txtViewMain.setText(fbUser.getEmail());
        }
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),MainActivity1.class);
                startActivity(intent);
                finish();

            }
        });

    }
}