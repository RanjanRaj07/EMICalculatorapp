package com.example.emilns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.emilns.databinding.ActivityLoginBinding;


public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);
        binding.loginBtn.setOnClickListener(view -> {
            String uname = binding.usernameId.getText().toString();
            System.out.println(uname);
            String pwd = binding.passwordId.getText().toString();

            if(uname.equals("")||pwd.equals(""))
                Toast.makeText(Login.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
            else{
                Boolean checkCredentials = databaseHelper.checkUserPassword(uname,pwd);
                if(checkCredentials==true) {
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("uname",uname);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.signupBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this,SignUp.class);
            startActivity(intent);
        });

        binding.signupRedirectText.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this,SignUp.class);
            startActivity(intent);
        });
    }
}