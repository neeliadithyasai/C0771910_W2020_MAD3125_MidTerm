package com.example.taxcalculationspecification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class detailsDisplay extends AppCompatActivity {

    private TextView sno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_display);

        sno = findViewById(R.id.txtsno);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        CRACustomer C2 = (CRACustomer) extras.getParcelable("object");

        sno.setText(C2.getSinNo());



    }
}
