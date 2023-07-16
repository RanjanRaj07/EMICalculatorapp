package com.example.emilns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.emilns.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.signupBtn.setOnClickListener(view -> {
            String email = binding.emailId.getText().toString();
            String password = binding.passwordId.getText().toString();
            String username = binding.usernameId.getText().toString();

            if(email.equals("")||password.equals("")||username.equals(""))
                Toast.makeText(SignUp.this,"All fields are mandatory",Toast.LENGTH_LONG).show();
            else{
                Boolean checkEmail = databaseHelper.checkEmail(email);
                if(checkEmail==false){
                    Boolean insert = databaseHelper.insertData(email,username,password);
                    if(insert==true){
                        Toast.makeText(SignUp.this,"Signup Successful",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),Login.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SignUp.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(SignUp.this, "User already registered !!Login", Toast.LENGTH_SHORT).show();
            }
        });
        binding.loginRedirectText.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
        });
    }
}