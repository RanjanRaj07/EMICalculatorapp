package com.example.emilns;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.emiserver.IEmiAidlInterface;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    EditText pa,dp,ir,term;
    TextView welcometxt,result;
    IEmiAidlInterface iEmiAidlInterface;
    DecimalFormat formatter = new DecimalFormat("#0.00");

    public String currentuser;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iEmiAidlInterface = IEmiAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.calci_btn);
        btn.setOnClickListener(this);

        pa = findViewById(R.id.pa_text);

        dp = findViewById(R.id.dp_text);

        ir = findViewById(R.id.ir_text);

        term = findViewById(R.id.term_text);

        result = findViewById(R.id.result_text);

        welcometxt = findViewById(R.id.welcome_id);

        Intent intent = getIntent();
        currentuser = intent.getStringExtra("uname");
        welcometxt.append(currentuser);
        Intent i = new Intent("EmiCalculatorService");
        i.setPackage("com.example.emiserver");

//        Intent i = new Intent();
//        i.setComponent(new ComponentName("com.example.emiserver", "com.example.emiserver.EmiService"));

//        Intent i = new Intent("com.example.emiserver.EmiService");
//        i.setPackage("com.example.emiserver");

//        Intent i = new Intent(this,com.example.emiserver.EmiService.class);

        bindService(i,serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View view) {
        System.out.println("this called after onCreate");
        if(iEmiAidlInterface==null) System.out.println("interface obj null");
        try {
            long p = Long.parseLong(pa.getText().toString());
            long d = Long.parseLong(dp.getText().toString());
            float r = Float.parseFloat(ir.getText().toString());
            int n = Integer.parseInt(term.getText().toString());
            System.out.println(currentuser);
            double emi = iEmiAidlInterface.calculateEMI(currentuser,p,d,r,n);
            result.setText(formatter.format(emi));
            System.out.println(emi);
        }catch (RemoteException e){}
    }
}