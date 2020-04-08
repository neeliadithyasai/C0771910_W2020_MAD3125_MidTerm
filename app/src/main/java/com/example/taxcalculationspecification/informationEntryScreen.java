package com.example.taxcalculationspecification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class informationEntryScreen extends AppCompatActivity {

    private TextView DateTxt;
    private TextView Age;
    private TextView currentDate;
    private TextView sinNO;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DateTxt = findViewById(R.id.BirthDate);
        Age = findViewById(R.id.Age);
        currentDate = findViewById(R.id.CurrentDate);
        sinNO = findViewById(R.id.Sin);





        final Calendar myCalendar = Calendar.getInstance();
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        currentDate.setText( "Tax Filing Date: "+sdf.format(myCalendar.getTime()));
        currentDate.setTextColor(Color.BLACK);
        currentDate.setTypeface(null,Typeface.BOLD);



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                DateTxt.setText("Date of Birth: "+sdf.format(myCalendar.getTime()));
                DateTxt.setTextColor(Color.BLACK);

                if(calculateAge(myCalendar.getTimeInMillis()) > 18 )
                {
                    Age.setText("Age: " + Integer.toString(calculateAge(myCalendar.getTimeInMillis())));
                    Age.setTextColor(Color.BLACK);
                    Age.setTypeface(null,Typeface.NORMAL);


                }else {
                    Age.setText(" Not eligible to file tax for current year!");
                    Age.setTextColor(Color.RED);
                    Age.setTypeface(null, Typeface.BOLD);


                }


            }

        };

        DateTxt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(informationEntryScreen.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });




        sinNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strSin = sinNO.getText().toString();

                if((isValidSin(strSin)) == true){
                    sinNO.setText(strSin);
                }else
                {
                    sinNO.setError("not valid");
                }



            }
        });

    }

    int calculateAge(long date){
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if(today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)){
            age--;
        }
        return age;
    }
    private boolean isValidSin(String sin) {
        String sinValidation = "\\(\\d{3}\\)-\\d{3}-\\d{4}";

        Pattern pattern = Pattern.compile(sinValidation);
        Matcher matcher = pattern.matcher(sin);
        return matcher.matches();
    }





}