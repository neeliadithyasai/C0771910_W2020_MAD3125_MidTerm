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

        sno = findViewById(R.id.sinNumber);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        CRACustomer C2 = (CRACustomer) extras.getParcelable("object");

        sno.setText(C2.getCurrentDate()+"\n"+C2.getSinNo()+"\n"+C2.getFirstName()+"\n"+C2.getLastName()+"\n"+C2.getBirthdate()+"\n"+C2.getAge()+"\n"+C2.getGender()+"\n"+C2.getGrossIncome()+"\n"+C2.getRRSP());



    }
}
