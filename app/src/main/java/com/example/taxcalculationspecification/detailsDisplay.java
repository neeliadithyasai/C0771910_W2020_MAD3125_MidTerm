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
    private TextView ttincome;
    private TextView provincial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_display);

       cpp = findViewById(R.id.cppp);
       eI = findViewById(R.id.eIp);
       rrsp = findViewById(R.id.rrspcon);
       cfrrsp =findViewById(R.id.cfrrsppp);
       ttincome = findViewById(R.id.ttip);
       provincial = findViewById(R.id.provincialTax);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        CRACustomer C2 = (CRACustomer) extras.getParcelable("object");

        Double gross = C2.getGrossIncome();
        Double contributedRrsp = C2.getRRSP();
        Double TotalTaxableIncome = (gross-(calcualtecpp(gross)+calculateEi(gross)+calculateMaxRrsp(gross)));

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

       ttincome.setText(TotalTaxableIncome.toString());

       if(TotalTaxableIncome > 220000){
           Double pt = TotalTaxableIncome*0.1316;
           provincial.setText(pt.toString());

       }else if ((TotalTaxableIncome >= 150000) &&(TotalTaxableIncome <= 220000)){
           Double pt = TotalTaxableIncome*0.1216;
           provincial.setText(pt.toString());
       }else if ((TotalTaxableIncome >= 87813.01) &&(TotalTaxableIncome <= 150000)){
           Double pt = TotalTaxableIncome*0.1116;
           provincial.setText(pt.toString());
       }else if ((TotalTaxableIncome >= 43906.01) &&(TotalTaxableIncome <= 87813)){
           Double pt = TotalTaxableIncome*0.0915;
           provincial.setText(pt.toString());
       }else if ((TotalTaxableIncome >= 10582.01) &&(TotalTaxableIncome <= 43906)){
           Double pt = TotalTaxableIncome*0.0505;
           provincial.setText(pt.toString());
       }else if (TotalTaxableIncome <= 10582){
           Double pt = TotalTaxableIncome;
           provincial.setText(pt.toString());
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
