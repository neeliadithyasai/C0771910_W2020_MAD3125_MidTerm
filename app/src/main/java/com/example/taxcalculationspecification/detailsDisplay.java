package com.example.taxcalculationspecification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class detailsDisplay extends AppCompatActivity {

    private TextView cpp;
    private TextView eI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_display);

       cpp = findViewById(R.id.cppp);
       eI = findViewById(R.id.eIp);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        CRACustomer C2 = (CRACustomer) extras.getParcelable("object");

        Double gross = C2.getGrossIncome();

       cpp.setText(calcualtecpp(gross).toString());
       eI.setText(calculateEi(gross).toString());



      //  sno.setText(C2.getCurrentDate()+"\n"+C2.getSinNo()+"\n"+C2.getFirstName()+"\n"+C2.getLastName()+"\n"+C2.getBirthdate()+"\n"+C2.getAge()+"\n"+C2.getGender()+"\n"+C2.getGrossIncome()+"\n"+C2.getRRSP());




    }


    private Double calcualtecpp(Double gross)
    {
        Double x;
        Double y;
        if(gross < 57400){
            x = 57400 - gross;
            y = 57400 -x;
            x = y * 0.051;

        }else
        {
            x = gross - 57400;
            y = gross - x;
            x = y * 0.051;
        }

        return x;

    }
   

}
