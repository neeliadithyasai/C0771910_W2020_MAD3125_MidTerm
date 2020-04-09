package com.example.taxcalculationspecification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class detailsDisplay extends AppCompatActivity {

    private TextView cpp;
    private TextView eI;
    private TextView cfrrsp;
    private TextView rrsp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_display);

       cpp = findViewById(R.id.cppp);
       eI = findViewById(R.id.eIp);
       rrsp = findViewById(R.id.rrspcon);
       cfrrsp =findViewById(R.id.cfrrsppp);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        CRACustomer C2 = (CRACustomer) extras.getParcelable("object");

        Double gross = C2.getGrossIncome();
        Double contributedRrsp = C2.getRRSP();

       cpp.setText(calcualtecpp(gross).toString());
       eI.setText(calculateEi(gross).toString());

       if(calculateMaxRrsp(gross) > contributedRrsp)
       {
          Double x = calculateMaxRrsp(gross) - contributedRrsp;
          cfrrsp.setText(x.toString());
          rrsp.setText(contributedRrsp.toString());

       }else if(calculateMaxRrsp(gross) < contributedRrsp)
       {
           Double x = calculateMaxRrsp(gross) - contributedRrsp;
           cfrrsp.setText(x.toString());
           cfrrsp.setTextColor(Color.RED);
           rrsp.setText(contributedRrsp.toString());

       }



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
    private Double calculateEi(Double gross){
        Double x;

        if(gross >= 53100){

            x = 860.22;

        }else{

            x = gross *0.0162;

        }

        return x;
    }

    private Double calculateMaxRrsp(Double gross){
        Double x;

        x = gross * 0.18;

        return x;


    }


}
