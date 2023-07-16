package com.example.emiserver;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;


public class EmiService extends Service {

    private void saveEmiToSharedPreferences(String client,double emi,double pa,double dp,float r,int n) {
        SharedPreferences sharedPreferences = getSharedPreferences("EMI_PREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("emi", (float) emi);
        editor.putFloat("pa",(float) pa);
        editor.putFloat("dp",(float) dp);
        editor.putFloat("r",(float) r);
        editor.putFloat("n",(float) n);
        editor.putString("client",client);
        editor.apply();
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }
    private final IEmiAidlInterface.Stub binder = new IEmiAidlInterface.Stub() {
        @Override
        public double calculateEMI(String client,double pa, double dp, float r, int n) throws RemoteException {
            System.out.println("client called");
            double par=pa;
            pa = pa - dp;
            r = r/(12 * 100); // per month interest rate
            double emi = (pa * r * Math.pow((1 + r), n))
                    / (Math.pow((1 + r), n) - 1);
            System.out.println("emi calculated "+emi);
            saveEmiToSharedPreferences(client,emi,par,dp,r,n);
            return emi;
        }
    };

}
