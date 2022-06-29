package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MainActivity;

public class AddActivity  extends AppCompatActivity {

    private Object MyKotlinFileName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



    }

    public static int Add(int a, int b){

        return ( a+b);

    }
    public  int[] solutions(int[] A, int K){
        for(int i=0; i<K; i++){
            int lastValue=A[A.length-1];
            for(int j= A.length-1;j<=0;j--){
                A[j+1]=A[j];
            }
            A[0]=lastValue;

        }
        return A;
    }
}
