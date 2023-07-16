// IEmiAidlInterface.aidl
package com.example.emiserver;

// Declare any non-default types here with import statements

interface IEmiAidlInterface {
    double calculateEMI(String client,double pa,double dp,float r,int n);
}