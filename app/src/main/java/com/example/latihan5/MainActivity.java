package com.example.latihan5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
    }

    //login
    public void login(View view){
        final String isiusername = username.getText().toString();
        final String isipassword = password.getText().toString();
        if(isiusername.equals("admin") && isipassword.equals("admin")){
            Intent i = new Intent(MainActivity.this, Menuutama.class);
            startActivity(i);
        }else{
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);
        }
    }
}