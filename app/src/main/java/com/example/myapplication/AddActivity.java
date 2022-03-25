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
}
