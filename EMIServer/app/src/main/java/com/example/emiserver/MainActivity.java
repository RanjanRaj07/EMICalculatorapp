package com.example.emiserver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public TextView pa,dp,ir,term,result,clientname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pa = findViewById(R.id.pa_id);
        pa.setText("");
        dp = findViewById(R.id.dp_id);
        dp.setText("");
        ir = findViewById(R.id.ir_id);
        ir.setText("");
        term = findViewById(R.id.n_id);
        term.setText("");
        result = findViewById(R.id.res_id);
        result.setText("");
        clientname = findViewById(R.id.clientname_id);
        clientname.setText("");
        Intent serviceIntent = new Intent(this, EmiService.class);
        startService(serviceIntent);
    }
    @Override
    protected void onResume() {
        super.onResume();

        // Retrieve the EMI value from Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences("EMI_PREFS", Context.MODE_PRIVATE);
        float emi = sharedPreferences.getFloat("emi", 0.0f);
        float par = sharedPreferences.getFloat("pa", 0.0f);
        float dpr = sharedPreferences.getFloat("dp", 0.0f);
        float rr = sharedPreferences.getFloat("r", 0.0f);
        float nr = sharedPreferences.getFloat("n", 0.0f);
        String client = sharedPreferences.getString("client","non");
        String emiString = String.valueOf(emi);
        clientname.setText(client);
        pa.setText(String.valueOf(par));
        dp.setText(String.valueOf(dpr));
        ir.setText(String.valueOf(rr*100));
        ir.append("%");
        term.setText(String.valueOf(nr));
        result.setText(emiString);
    }

}